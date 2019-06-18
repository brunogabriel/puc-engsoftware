package io.github.brunogabriel.doggieapp.shared.network

import io.github.brunogabriel.doggieapp.shared.models.UserAuthenticated
import io.github.brunogabriel.doggieapp.shared.models.UserAuthenticationData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

/**
 * Created by brunogabriel on 2019-06-15.
 */
interface AuthenticationService {
    @POST("/login")
    fun authenticate(@Body userAuthenticationData: UserAuthenticationData) : Observable<Response<UserAuthenticated>>
}