package io.github.brunogabriel.doggieapp.shared.models

/**
 * Created by brunogabriel on 2019-06-19.
 */
data class Service(
    val id: Long,
    val petId: Long,
    val type: String,
    val duration: Int,
    val price: Double,
    val date: String,
    val employee: String,
    val products: List<String>?,
    val annotations: List<String>?)