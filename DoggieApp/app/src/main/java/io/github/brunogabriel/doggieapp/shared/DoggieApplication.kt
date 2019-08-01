package io.github.brunogabriel.doggieapp.shared

import android.app.Application
import io.github.brunogabriel.doggieapp.shared.network.RetrofitManager

/**
 * Created by brunogabriel on 2019-06-15.
 */
@Suppress("unused")
class DoggieApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        RetrofitManager.build(this)
    }
}