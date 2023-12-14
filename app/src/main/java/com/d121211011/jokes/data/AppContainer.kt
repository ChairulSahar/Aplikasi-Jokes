package com.d121211011.jokes.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.d121211011.jokes.data.repository.JokesRepository
import com.d121211011.jokes.data.source.remote.ApiService
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val jokesRepository: JokesRepository
}

class DefaultAppContainer: AppContainer {

    private val BASE_URL = "https://official-joke-api.appspot.com"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()

    private val retrofitService : ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

    override val jokesRepository: JokesRepository
        get() = JokesRepository(retrofitService)

}