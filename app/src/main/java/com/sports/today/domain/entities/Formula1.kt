package com.sports.today.domain.entities

import com.google.gson.annotations.SerializedName

data class Formula1(
    @field:SerializedName("publicationDate") val publicationDate: String,
    @field:SerializedName("seconds") val seconds: Double,
    @field:SerializedName("tournament") val tournament: String,
    @field:SerializedName("winner") val winner: String
)
