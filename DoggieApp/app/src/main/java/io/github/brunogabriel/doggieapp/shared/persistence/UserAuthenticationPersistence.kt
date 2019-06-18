package io.github.brunogabriel.doggieapp.shared.persistence

import android.content.Context
import io.github.brunogabriel.doggieapp.shared.models.UserAuthenticated
import java.lang.Exception

/**
 * Created by brunogabriel on 2019-06-15.
 */
class UserAuthenticationPersistence(private val context: Context) {

    companion object {
        private const val USER_AUTHENTICATED = "user_authenticated"
        private const val USERNAME = "username"
        private const val TOKEN = "token"
    }

    fun clearAll() {
        context.getSharedPreferences(USER_AUTHENTICATED, Context.MODE_PRIVATE).edit().clear().commit()
    }

    fun save(userAuthenticated: UserAuthenticated) {
        with(context.getSharedPreferences(USER_AUTHENTICATED, Context.MODE_PRIVATE).edit()) {
            putString(USERNAME, userAuthenticated.username)
            putString(TOKEN, userAuthenticated.token)
            commit()
        }
    }

    fun loadUserAuthenticated(): UserAuthenticated? {
        return try {
            val prefs = context.getSharedPreferences(USER_AUTHENTICATED, Context.MODE_PRIVATE)
            val username = prefs.getString(USERNAME, null)
            val token = prefs.getString(TOKEN, null)

            if (username != null && username.isNotEmpty() && token != null && token.isNotEmpty()) {
                UserAuthenticated(username, token)
            } else {
                null
            }
        } catch (exception: Exception) {
            null
        }
    }
}