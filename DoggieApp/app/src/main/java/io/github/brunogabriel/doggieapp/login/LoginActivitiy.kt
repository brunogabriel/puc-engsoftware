package io.github.brunogabriel.doggieapp.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.github.brunogabriel.doggieapp.R
import io.github.brunogabriel.doggieapp.main.MainActivity
import kotlinx.android.synthetic.main.activity_login.*

/**
 * Created by brunogabriel on 2019-06-15.
 */
class LoginActivitiy : AppCompatActivity(), LoginContract.View {

    override lateinit var presenter: LoginContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setupView()
    }

    private fun setupView() {
        login_button.setOnClickListener { showMain() }
    }

    override fun showMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}