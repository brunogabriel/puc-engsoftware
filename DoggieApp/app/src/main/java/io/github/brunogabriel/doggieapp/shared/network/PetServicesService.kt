package io.github.brunogabriel.doggieapp.shared.network

import io.github.brunogabriel.doggieapp.shared.models.Service
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import rx.Observable

/**
 * Created by brunogabriel on 2019-06-15.
 */
interface PetServicesService {

    @GET("/api/services/{petId}")
    fun findServices(@Header("Authorization") authorization: String,
                 @Path("petId") petId: Long): Observable<Response<List<Service>>>
}