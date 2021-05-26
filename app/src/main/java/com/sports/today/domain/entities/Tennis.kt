package com.sports.today.domain.entities

import com.google.gson.annotations.SerializedName

data class Tennis(
    @field:SerializedName("looser") val looser: String,
    @field:SerializedName("numberOfSets") val numberOfSets: Int,
    @field:SerializedName("publicationDate") val publicationDate: String,
    @field:SerializedName("tournament") val tournament: String,
    @field:SerializedName("winner") val winner: String
)