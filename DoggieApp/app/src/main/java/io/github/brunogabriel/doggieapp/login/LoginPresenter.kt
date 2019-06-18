package io.github.brunogabriel.doggieapp.login

import io.github.brunogabriel.doggieapp.shared.models.UserAuthenticated
import io.github.brunogabriel.doggieapp.shared.models.UserAuthenticationData
import io.github.brunogabriel.doggieapp.shared.network.AuthenticationService
import io.github.brunogabriel.doggieapp.shared.network.RetrofitManager
import io.github.brunogabriel.doggieapp.shared.persistence.UserAuthenticationPersistence
import retrofit2.Response
import rx.Scheduler
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by brunogabriel on 2019-06-15.
 */
class LoginPresenter(private val view: LoginContract.View,
                     private val userAuthenticationPersistence: UserAuthenticationPersistence,
                     private val userAuthenticationService: AuthenticationService =
                         RetrofitManager.createService(AuthenticationService::class.java),
                     private val subscribeOnScheduler: Scheduler = Schedulers.io(),
                     private val observeOnScheduler: Scheduler = AndroidSchedulers.mainThread()) : LoginContract.Presenter {

    override fun onLoginSelected(username: String, password: String) {
        var hasInvalidFields = false

        if (username.length < 3) {
            hasInvalidFields = true
            view.showInvalidUsername()
        }

        if (password.length < 6) {
            hasInvalidFields = true
            view.showInvalidPassword()
        }

        if (!hasInvalidFields) {
            userAuthenticationService.authenticate(UserAuthenticationData(username, password))
                .subscribeOn(subscribeOnScheduler)
                .observeOn(observeOnScheduler)
                .doOnSubscribe { view.showLoading() }
                .doAfterTerminate { view.dismissLoading() }
                .subscribe({
                    if (it.isSuccessful && it.body() != null) {
                        userAuthenticationPersistence.save(it.body()!!)
                        view.showMain()
                    } else {
                        view.showError()
                    }
                }, {
                    view.showError()
                })
        }
    }

}