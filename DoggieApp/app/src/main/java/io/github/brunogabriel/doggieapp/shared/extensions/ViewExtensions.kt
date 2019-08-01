package io.github.brunogabriel.doggieapp.shared.extensions

import android.widget.ImageView
import com.google.android.material.textfield.TextInputEditText
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

/**
 * Created by brunogabriel on 2019-06-15.
 */
fun ImageView.loadImage(url: String, callback: Callback? = null, canFit: Boolean = true) {
    Picasso
        .get()
        .load(url)
        .apply { if(canFit) fit() }
        .into(this, callback)
}

fun TextInputEditText.stringText(): String {
    return text?.toString() ?: ""
}