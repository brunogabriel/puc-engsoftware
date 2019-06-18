package io.github.brunogabriel.doggieapp.shared.network

import io.github.brunogabriel.doggieapp.shared.models.Pet
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import rx.Observable

/**
 * Created by brunogabriel on 2019-06-15.
 */
interface PetService {

    @GET("/api/pets/{clientId}")
    fun findPets(@Header("Authorization") authorization: String,
                 @Path("clientId") clientId: String): Observable<Response<List<Pet>>>
}