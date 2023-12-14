package com.d121211011.jokes.data.repository

import com.d121211011.jokes.data.response.GetJokesResponse
import com.d121211011.jokes.data.source.remote.ApiService

class JokesRepository(private val apiService: ApiService) {

    suspend fun getJokes(): GetJokesResponse {
        return apiService.getJokes()
    }

}