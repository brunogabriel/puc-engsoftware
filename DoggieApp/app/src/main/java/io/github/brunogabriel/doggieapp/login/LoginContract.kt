package io.github.brunogabriel.doggieapp.login

import io.github.brunogabriel.doggieapp.shared.arch.BaseView

/**
 * Created by brunogabriel on 2019-06-15.
 */
interface LoginContract {

    interface View : BaseView<Presenter> {
        fun showMain()
    }

    interface Presenter {

    }
}