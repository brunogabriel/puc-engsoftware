package io.github.brunogabriel.doggieapp.petdetails

import io.github.brunogabriel.doggieapp.shared.models.Pet

class PetDetailsPresenter(private val view: PetDetailsContract.View,
                          private val pet: Pet) : PetDetailsContract.Presenter {

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

        // TODO: request services
    }

}
