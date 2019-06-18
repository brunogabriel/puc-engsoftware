package io.github.brunogabriel.doggieapp.shared.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by brunogabriel on 2019-06-15.
 */
@Parcelize
data class UserAuthenticated(val username: String, val token: String) : Parcelable