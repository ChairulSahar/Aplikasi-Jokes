package com.d121211011.jokes.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Jokes (
    @SerialName("type")
    val type: String?,
    @SerialName("setup")
    val setup: String?,
    @SerialName("punchline")
    val punchline: String?,
    @SerialName("id")
    val id: Int?
) : Parcelable