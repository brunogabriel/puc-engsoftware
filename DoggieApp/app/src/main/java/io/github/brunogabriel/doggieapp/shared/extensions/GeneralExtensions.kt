package io.github.brunogabriel.doggieapp.shared.extensions

import android.content.res.Resources
import java.lang.Exception

/**
 * Created by brunogabriel on 2019-06-15.
 */
fun Int.pxToDp(): Int {
    return try {
        return (Resources.getSystem().displayMetrics.density * this).toInt()
    } catch (exception: Exception) {
        32
    }
}