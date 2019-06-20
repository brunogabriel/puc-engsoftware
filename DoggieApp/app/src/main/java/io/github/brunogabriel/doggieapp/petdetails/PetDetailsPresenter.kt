package io.github.brunogabriel.doggieapp.petdetails

import io.github.brunogabriel.doggieapp.shared.models.Pet
import io.github.brunogabriel.doggieapp.shared.models.UserAuthenticated
import io.github.brunogabriel.doggieapp.shared.network.PetServicesService
import io.github.brunogabriel.doggieapp.shared.network.RetrofitManager
import rx.Scheduler
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class PetDetailsPresenter(private val view: PetDetailsContract.View,
                          private val pet: Pet,
                          private val userAuthenticated: UserAuthenticated,
                          private val petServicesService: PetServicesService = RetrofitManager.createService(PetServicesService::class.java),
                          private val subscribeOnScheduler: Scheduler = Schedulers.io(),
                          private val observeOnScheduler: Scheduler = AndroidSchedulers.mainThread()) : PetDetailsContract.Presenter {

    override fun initialize() {
        view.showPetDetails(pet.name, pet.type, pet.gait)

        if (pet.images != null && pet.images.isNotEmpty()) {
            view.showPetImages(pet.images)
        }

        if (pet.allergies != null && pet.allergies.isNotEmpty()) {
            view.showPetAllergies(pet.allergies)
        }

        if (pet.preferences != null && pet.preferences.isNotEmpty()) {
            view.showPetPreferences(pet.preferences)
        }

        if (pet.habits != null && pet.habits.isNotEmpty()) {
            view.showPetHabits(pet.habits)
        }

        requestServices()
    }

    override fun onSelectedTryAgain() {
        requestServices()
    }

    private fun requestServices() {
        petServicesService.findServices(userAuthenticated.token, pet.id)
            .subscribeOn(subscribeOnScheduler)
            .observeOn(observeOnScheduler)
            .doOnSubscribe { view.showLoading() }
            .doAfterTerminate { view.dismissLoading() }
            .subscribe({
                if (it.isSuccessful && it.body() != null) {
                    if (it.body()!!.isEmpty()) {
                        view.showEmptyResult()
                    } else {
                        view.showServices(it.body()!!)
                    }
                } else {
                    view.showTryAgain()
                }

            }, {
                view.showTryAgain()
            })
    }

}
