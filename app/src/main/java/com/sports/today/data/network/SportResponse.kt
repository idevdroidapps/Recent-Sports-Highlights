package com.sports.today.data.network

import com.google.gson.annotations.SerializedName
import com.sports.today.domain.entities.Basketball
import com.sports.today.domain.entities.Formula1
import com.sports.today.domain.entities.Tennis

data class SportResponse(
    @SerializedName("f1Results") val f1Results: List<Formula1>,
    @SerializedName("nbaResults") val nbaResults: List<Basketball>,
    @SerializedName("Tennis") val tennisResults: List<Tennis>
)