package io.github.brunogabriel.doggieapp.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.github.brunogabriel.doggieapp.login.LoginActivitiy

/**
 * Created by brunogabriel on 2019-06-15.
 */
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, LoginActivitiy::class.java))
        finish()
    }
}