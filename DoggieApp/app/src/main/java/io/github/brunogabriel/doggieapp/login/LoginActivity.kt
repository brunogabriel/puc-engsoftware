package io.github.brunogabriel.doggieapp.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.jakewharton.rxbinding3.widget.textChanges
import io.github.brunogabriel.doggieapp.R
import io.github.brunogabriel.doggieapp.main.MainActivity
import io.github.brunogabriel.doggieapp.shared.extensions.stringText
import io.github.brunogabriel.doggieapp.shared.persistence.UserAuthenticationPersistence
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_login.*
import rx.subscriptions.CompositeSubscription

/**
 * Created by brunogabriel on 2019-06-15.
 */
class LoginActivity : AppCompatActivity(), LoginContract.View {

    override lateinit var presenter: LoginContract.Presenter
    private val compositeSubscription = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setupView()
        presenter = LoginPresenter(this, UserAuthenticationPersistence(this))
    }

    private fun setupView() {
        login_button.setOnClickListener {
            presenter.onLoginSelected(user_edit_text.stringText(), password_edit_text.stringText())
        }

        compositeSubscription.addAll(user_edit_text.textChanges().subscribe({
            user_input_layout.error = null
        }, {}), password_edit_text.textChanges().subscribe({
            password_input_layout.error = null
        }, {}))

    }

    override fun onDestroy() {
        compositeSubscription.apply {
            clear()
            dispose()
        }
        super.onDestroy()
    }

    override fun showMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun showInvalidUsername() {
        user_input_layout.error = getString(R.string.invalid_username)
    }

    override fun showInvalidPassword() {
        password_input_layout.error = getString(R.string.invalid_password)
    }

    override fun showLoading() {
        currentFocus?.let {
            val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(it.windowToken, 0)
        }
        main_view.visibility = View.GONE
        loading_view.visibility = View.VISIBLE
    }

    override fun dismissLoading() {
        main_view.visibility = View.VISIBLE
        loading_view.visibility = View.GONE
    }

    override fun showError() {
        AlertDialog.Builder(this)
            .setCancelable(false)
            .setMessage(R.string.user_not_found_message)
            .setPositiveButton(R.string.understood, null)
            .show()
    }

}