package io.github.brunogabriel.doggieapp.main.petlist

import io.github.brunogabriel.doggieapp.shared.arch.BaseView
import io.github.brunogabriel.doggieapp.shared.models.Pet

interface PetListContract {
    interface View : BaseView<Presenter> {
        fun showPets(pets: List<Pet>)
        fun showEmptyResult()
        fun showTryAgain()
        fun showLoading()
        fun dismissLoading()
    }

    interface Presenter {
    }
}
