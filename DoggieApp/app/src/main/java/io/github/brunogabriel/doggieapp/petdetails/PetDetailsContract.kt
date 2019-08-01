package io.github.brunogabriel.doggieapp.petdetails

import io.github.brunogabriel.doggieapp.shared.arch.BaseView
import io.github.brunogabriel.doggieapp.shared.models.Service
import io.github.brunogabriel.doggieapp.shared.view.LoadingView

interface PetDetailsContract {

    interface View : BaseView<Presenter>, LoadingView {
        fun showPetImages(images: List<String>)
        fun showPetDetails(name: String, type: String, gait: String?)
        fun showPetAllergies(allergies: List<String>)
        fun showPetHabits(habits: List<String>)
        fun showPetPreferences(preferences: List<String>)
        fun showEmptyResult()
        fun showServices(body: List<Service>)
        fun showTryAgain()
    }

    interface Presenter {
        fun initialize()
        fun onSelectedTryAgain()
    }
}
