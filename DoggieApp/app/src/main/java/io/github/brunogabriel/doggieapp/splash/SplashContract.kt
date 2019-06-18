package io.github.brunogabriel.doggieapp.splash

import io.github.brunogabriel.doggieapp.shared.arch.BaseView

interface SplashContract {

    interface View : BaseView<Presenter> {
        fun showLogin()
        fun showMain()
    }

    interface Presenter {
        fun initialize()
    }
}
