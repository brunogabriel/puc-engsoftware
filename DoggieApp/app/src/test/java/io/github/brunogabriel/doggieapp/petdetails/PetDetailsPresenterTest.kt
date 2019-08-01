package io.github.brunogabriel.doggieapp.petdetails

import com.nhaarman.mockitokotlin2.*
import io.github.brunogabriel.doggieapp.shared.models.Pet
import io.github.brunogabriel.doggieapp.shared.models.Service
import io.github.brunogabriel.doggieapp.shared.models.UserAuthenticated
import io.github.brunogabriel.doggieapp.shared.network.PetServicesService
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import retrofit2.Response
import rx.Observable
import rx.schedulers.Schedulers

/**
 * Created by brunogabriel on 2019-06-20.
 */
class PetDetailsPresenterTest {

    companion object {
        private const val USER = "anyUser"
        private const val TOKEN = "anyToken"
        private const val PET_NAME = "anyPetName"
        private const val PET_TYPE = "anyPetType"
        private const val PET_GAIT = "anyPetGait"
        private const val PET_ID = 100L
    }

    private lateinit var view: PetDetailsContract.View
    private lateinit var pet: Pet
    private lateinit var userAuthenticated: UserAuthenticated
    private lateinit var petServicesService: PetServicesService
    private lateinit var presenter: PetDetailsPresenter

    @Before
    fun setUp() {
        view = mock()

        userAuthenticated = mock()
        petServicesService = mock()
        pet = mock()
        whenever(userAuthenticated.username).thenReturn(USER)
        whenever(userAuthenticated.token).thenReturn(TOKEN)
        whenever(pet.name).thenReturn(PET_NAME)
        whenever(pet.gait).thenReturn(PET_GAIT)
        whenever(pet.type).thenReturn(PET_TYPE)
        whenever(pet.id).thenReturn(PET_ID)
        whenever(petServicesService.findServices(TOKEN, PET_ID)).thenReturn(Observable.just(Response.success(emptyList())))
        presenter = PetDetailsPresenter(view, pet, userAuthenticated, petServicesService, Schedulers.immediate(), Schedulers.immediate())
    }

    @Test
    fun shouldShowPetDetailsWhenInitializeWithPetGait() {
        // when
        presenter.initialize()

        // then
        verify(view).showPetDetails(PET_NAME, PET_TYPE, PET_GAIT)
    }

    @Test
    fun shouldShowPetDetailsWhenInitializeWithoutPetGait() {
        // given
        whenever(pet.gait).thenReturn(null)
        presenter = PetDetailsPresenter(view, pet, userAuthenticated, petServicesService, Schedulers.immediate(), Schedulers.immediate())


        // when
        presenter.initialize()

        // then
        verify(view).showPetDetails(PET_NAME, PET_TYPE, null)
    }

    @Test
    fun shouldNotShowPetImagesWhenInitialize() {
        // when
        presenter.initialize()

        // then
        verify(view, never()).showPetImages(any())
    }

    @Test
    fun shouldShowPetImagesWhenInitialize() {
        // given
        val listItems = listOf("1", "2")
        whenever(pet.images).thenReturn(listItems)
        presenter = PetDetailsPresenter(view, pet, userAuthenticated, petServicesService, Schedulers.immediate(), Schedulers.immediate())

        // when
        presenter.initialize()

        // then
        verify(view).showPetImages(listItems)
    }

    @Test
    fun shouldNotShowPetAllergiesWhenInitialize() {
        // when
        presenter.initialize()

        // then
        verify(view, never()).showPetAllergies(any())
    }

    @Test
    fun shouldShowPetAllergiesWhenInitialize() {
        // given
        val listItems = listOf("1", "2")
        whenever(pet.allergies).thenReturn(listItems)
        presenter = PetDetailsPresenter(view, pet, userAuthenticated, petServicesService, Schedulers.immediate(), Schedulers.immediate())

        // when
        presenter.initialize()

        // then
        verify(view).showPetAllergies(listItems)
    }

    @Test
    fun shouldNotShowPetPreferencesWhenInitialize() {
        // when
        presenter.initialize()

        // then
        verify(view, never()).showPetPreferences(any())
    }

    @Test
    fun shouldShowPetPreferencesWhenInitialize() {
        // given
        val listItems = listOf("1", "2")
        whenever(pet.preferences).thenReturn(listItems)
        presenter = PetDetailsPresenter(view, pet, userAuthenticated, petServicesService, Schedulers.immediate(), Schedulers.immediate())

        // when
        presenter.initialize()

        // then
        verify(view).showPetPreferences(listItems)
    }

    @Test
    fun shouldNotShowPetHabitsWhenInitialize() {
        // when
        presenter.initialize()

        // then
        verify(view, never()).showPetHabits(any())
    }

    @Test
    fun shouldhowPetHabitsWhenInitialize() {
        // given
        val listItems = listOf("1", "2")
        whenever(pet.habits).thenReturn(listItems)
        presenter = PetDetailsPresenter(view, pet, userAuthenticated, petServicesService, Schedulers.immediate(), Schedulers.immediate())

        // when
        presenter.initialize()

        // then
        verify(view).showPetHabits(listItems)
    }

    @Test
    fun shouldShowEmptyResultWhenInitialize() {
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
    fun shouldShowServicesWhenInitialize() {
        // given
        val services = listOf(Service(1L,1L, "type1", 100, 100.00, "date1", "doneBy1", emptyList(), emptyList()),
            Service(2L,2L, "type2", 150, 9900.00, "date2", "doneBy2", emptyList(), emptyList()))

        whenever(petServicesService.findServices(TOKEN, PET_ID)).thenReturn(Observable.just(Response.success(services)))
        presenter = PetDetailsPresenter(view, pet, userAuthenticated, petServicesService, Schedulers.immediate(), Schedulers.immediate())

        // when
        presenter.initialize()

        // then
        inOrder(view) {
            verify(view).showLoading()
            verify(view).showServices(services)
            verify(view).dismissLoading()
        }
    }

    @Test
    fun shouldTryAgainWhenInitialize() {
        // given
        whenever(petServicesService.findServices(TOKEN, PET_ID)).thenReturn(Observable.just(Response.error(404, ResponseBody.create(null, ""))))
        presenter = PetDetailsPresenter(view, pet, userAuthenticated, petServicesService, Schedulers.immediate(), Schedulers.immediate())

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
    fun shouldTryAgainWhenInitializeWithServiceException() {
        // given
        whenever(petServicesService.findServices(TOKEN, PET_ID)).thenReturn(Observable.error(Exception("Any exception message")))
        presenter = PetDetailsPresenter(view, pet, userAuthenticated, petServicesService, Schedulers.immediate(), Schedulers.immediate())

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
    fun shouldShowEmptyResultWhenOnSelectedTryAgain() {
        // when
        presenter.onSelectedTryAgain()

        // then
        inOrder(view) {
            verify(view).showLoading()
            verify(view).showEmptyResult()
            verify(view).dismissLoading()
        }
    }
}