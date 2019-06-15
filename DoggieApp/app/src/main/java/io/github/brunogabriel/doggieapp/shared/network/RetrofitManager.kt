package io.github.brunogabriel.doggieapp.shared.network

import android.content.Context
import io.github.brunogabriel.doggieapp.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by brunogabriel on 2019-06-15.
 */
object RetrofitManager {
    private const val CONNECTION_TIMEOUT = 40L
    private const val READ_TIMEOUT = 60L
    private var retrofit: Retrofit? = null
    private var okHttpClient: OkHttpClient? = null

    fun <T> createService(tClazz: Class<T>) = retrofit?.create(tClazz)!!

    fun build(context: Context? = null): Retrofit? {
        if (okHttpClient == null) {
            val okHttpClientBuilder = OkHttpClient.Builder()
            okHttpClient = okHttpClientBuilder
                .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .build()
        }

        if (retrofit == null) {
            retrofit = createRetrofit()
        }
        return retrofit
    }

    fun changeBaseURL(newBaseURL: String) {
        retrofit = createRetrofit(newBaseURL)
    }

    private fun createRetrofit(baseURL: String = BuildConfig.base_url): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseURL)
            .client(okHttpClient!!)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build()
    }
}