package io.github.brunogabriel.doggieapp.main.petlist

import com.nhaarman.mockitokotlin2.inOrder
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.github.brunogabriel.doggieapp.shared.models.Pet
import io.github.brunogabriel.doggieapp.shared.models.UserAuthenticated
import io.github.brunogabriel.doggieapp.shared.network.PetService
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import retrofit2.Response
import rx.Observable
import rx.schedulers.Schedulers
/**
 * Created by brunogabriel on 2019-06-17.
 */
class PetListPresenterTest {

    companion object {
        private const val USER = "anyUser"
        private const val TOKEN = "anyToken"
    }

    private lateinit var presenter: PetListPresenter
    private lateinit var view: PetListContract.View
    private lateinit var userAuthenticated: UserAuthenticated
    private lateinit var service: PetService


    @Before
    fun setUp() {
        view = mock()
        service = mock()
        userAuthenticated = mock()
        whenever(userAuthenticated.username).thenReturn(USER)
        whenever(userAuthenticated.token).thenReturn(TOKEN)
        presenter = PetListPresenter(view, userAuthenticated, service, Schedulers.immediate(), Schedulers.immediate())
    }

    @Test
    fun shouldShowTryAgainWhenInitializeWithAnyException() {
        // given
        whenever(service.findPets(TOKEN, USER)).thenReturn(Observable.error(Exception("Any exception message")))

        // when
        presenter.initialize()

        // then
        inOrder(view) {
           verify(view).showLoading()
           verify(view).showTryAgain()
           verify(view).dismissLoading()
        }
    }

    @Test
    fun shouldShowTryAgainWhenInitializeWithError() {
        // given
        whenever(service.findPets(TOKEN, USER)).thenReturn(Observable.just(Response.error(404, ResponseBody.create(null, ""))))

        // when
        presenter.initialize()

        // then
        inOrder(view) {
            verify(view).showLoading()
            verify(view).showTryAgain()
            verify(view).dismissLoading()
        }
    }

    @Test
    fun shouldShowEmptyResultWhenInitializeWithEmptyResult() {
        // given
        whenever(service.findPets(TOKEN, USER)).thenReturn(Observable.just(Response.success(emptyList())))

        // when
        presenter.initialize()

        // then
        inOrder(view) {
            verify(view).showLoading()
            verify(view).showEmptyResult()
            verify(view).dismissLoading()
        }
    }

    @Test
    fun shouldShowPetsWhenInitializeWithNoEmptyResult() {
        // given
        val pets = listOf(
            Pet(1L, "client1", "type1", "name1", null, emptyList(), emptyList(), emptyList(), emptyList(), 1),
            Pet(2L, "client2", "type2", "name2", null, emptyList(), emptyList(), emptyList(), emptyList(), 2))
        whenever(service.findPets(TOKEN, USER)).thenReturn(Observable.just(Response.success(pets)))

        // when
        presenter.initialize()

        // then
        inOrder(view) {
            verify(view).showLoading()
            verify(view).showPets(pets)
            verify(view).dismissLoading()
        }
    }
}