package io.github.brunogabriel.doggieapp.splash

import io.github.brunogabriel.doggieapp.shared.persistence.UserAuthenticationPersistence

class SplashPresenter(private val view: SplashContract.View,
                      private val userAuthenticationPersistence: UserAuthenticationPersistence) : SplashContract.Presenter {
    override fun initialize() {
        if (userAuthenticationPersistence.loadUserAuthenticated() != null) {
            view.showMain()
        } else {
            view.showLogin()
        }
    }

}
