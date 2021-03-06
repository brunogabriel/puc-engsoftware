package io.github.brunogabriel.doggieapp.shared.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by brunogabriel on 2019-06-15.
 */
@Parcelize
class Pet(
    val id: Long,
    val clientId: String,
    val type: String,
    val name: String,
    val gait: String?,
    val allergies: List<String>?,
    val habits: List<String>?,
    val preferences: List<String>?,
    val images:  List<String>?,
    val quantityOfServices: Int = 0
) : Parcelable