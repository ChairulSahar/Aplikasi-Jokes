package com.d121211011.jokes.data.source.remote

import com.d121211011.jokes.data.response.GetJokesResponse
import retrofit2.http.GET

interface ApiService {

    @GET("random_ten")
    suspend fun getJokes(
    ): GetJokesResponse

}