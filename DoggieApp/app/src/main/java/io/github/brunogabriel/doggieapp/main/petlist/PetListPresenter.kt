package io.github.brunogabriel.doggieapp.main.petlist

import io.github.brunogabriel.doggieapp.shared.models.UserAuthenticated
import io.github.brunogabriel.doggieapp.shared.network.PetService
import io.github.brunogabriel.doggieapp.shared.network.RetrofitManager
import rx.Scheduler
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by brunogabriel on 2019-06-17.
 */
class PetListPresenter(private val view: PetListContract.View,
                       private val userAuthenticated: UserAuthenticated,
                       private val petsService: PetService = RetrofitManager.createService(PetService::class.java),
                       private val subscribeOnScheduler: Scheduler = Schedulers.io(),
                       private val observeOnScheduler: Scheduler = AndroidSchedulers.mainThread())
    : PetListContract.Presenter {

    override fun initialize() {
        petsService.findPets(userAuthenticated.token, userAuthenticated.username)
            .subscribeOn(subscribeOnScheduler)
            .observeOn(observeOnScheduler)
            .doOnSubscribe { view.showLoading() }
            .doAfterTerminate { view.dismissLoading() }
            .subscribe({
                if (it.isSuccessful && it.body() != null) {
                    if (it.body()!!.isEmpty()) {
                        view.showEmptyResult()
                    } else {
                        view.showPets(it.body()!!)
                    }
                } else {
                    view.showTryAgain()
                }
            }, {
                view.showTryAgain()
            })
    }
}