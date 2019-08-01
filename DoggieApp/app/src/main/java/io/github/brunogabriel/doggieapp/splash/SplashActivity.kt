package io.github.brunogabriel.doggieapp.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.github.brunogabriel.doggieapp.login.LoginActivity
import io.github.brunogabriel.doggieapp.main.MainActivity
import io.github.brunogabriel.doggieapp.shared.persistence.UserAuthenticationPersistence

/**
 * Created by brunogabriel on 2019-06-15.
 */
class SplashActivity : AppCompatActivity(), SplashContract.View {

    override lateinit var presenter: SplashContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = SplashPresenter(this, UserAuthenticationPersistence(this)).apply {
            initialize()
        }
    }

    override fun showLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    override fun showMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

}