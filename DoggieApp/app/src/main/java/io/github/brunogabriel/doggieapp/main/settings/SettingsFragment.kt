package io.github.brunogabriel.doggieapp.main.settings

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import io.github.brunogabriel.doggieapp.R
import io.github.brunogabriel.doggieapp.login.LoginActivity
import io.github.brunogabriel.doggieapp.shared.persistence.UserAuthenticationPersistence
import kotlinx.android.synthetic.main.fragment_settings.*

/**
 * Created by brunogabriel on 2019-06-15.
 */
class SettingsFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return LayoutInflater.from(context).inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        welcome_message.text = getString(R.string.welcome_pattern, UserAuthenticationPersistence(context!!).loadUserAuthenticated()!!.username.capitalize())
        logout_button.setOnClickListener {
            UserAuthenticationPersistence(context!!).clearAll()
            activity!!.apply {
                AlertDialog.Builder(context!!)
                    .setPositiveButton(R.string.yes_text) { dialog, _ ->
                        dialog.dismiss()
                        logout()
                    }
                    .setNegativeButton(R.string.no_text, null)
                    .setMessage(R.string.logout_message)
                    .show()
            }
        }
    }

    private fun logout() {
        UserAuthenticationPersistence(context!!).clearAll()
        activity!!.apply {
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }
    }
}