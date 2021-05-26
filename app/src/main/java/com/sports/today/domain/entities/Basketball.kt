package com.sports.today.domain.entities

import com.google.gson.annotations.SerializedName

data class Basketball(
    @field:SerializedName("gameNumber") val gameNumber: Int,
    @field:SerializedName("looser") val looser: String,
    @field:SerializedName("mvp") val mvp: String,
    @field:SerializedName("publicationDate") val publicationDate: String,
    @field:SerializedName("tournament") val tournament: String,
    @field:SerializedName("winner") val winner: String
)