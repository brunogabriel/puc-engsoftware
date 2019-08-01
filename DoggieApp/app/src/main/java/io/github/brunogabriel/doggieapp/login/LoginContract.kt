package io.github.brunogabriel.doggieapp.login

import io.github.brunogabriel.doggieapp.shared.arch.BaseView
import io.github.brunogabriel.doggieapp.shared.view.LoadingView

/**
 * Created by brunogabriel on 2019-06-15.
 */
interface LoginContract {

    interface View : BaseView<Presenter>, LoadingView {
        fun showMain()
        fun showError()
        fun showInvalidUsername()
        fun showInvalidPassword()
    }

    interface Presenter {
        fun onLoginSelected(username: String, password: String)
    }
}

