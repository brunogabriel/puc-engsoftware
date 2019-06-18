package io.github.brunogabriel.doggieapp.petdetails

import io.github.brunogabriel.doggieapp.shared.arch.BaseView
import io.github.brunogabriel.doggieapp.shared.models.Pet

interface PetDetailsContract {

    interface View : BaseView<Presenter> {
        fun showPetImages(images: List<String>)
        fun showPetDetails(name: String, type: String, gait: String?)
        fun showPetAllergies(allergies: List<String>)
        fun showPetHabits(habits: List<String>)
        fun showPetPreferences(preferences: List<String>)
    }

    interface Presenter {
        fun initialize()
    }
}
