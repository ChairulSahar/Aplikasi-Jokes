package com.d121211011.jokes.data.response

import com.d121211011.jokes.data.models.Jokes
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetJokesResponse(
    @SerialName("jokes")
    val jokes: List<Jokes>
)