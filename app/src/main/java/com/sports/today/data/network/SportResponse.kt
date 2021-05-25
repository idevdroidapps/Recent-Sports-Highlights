package com.sports.today.data.network

import com.google.gson.annotations.SerializedName
import com.sports.today.domain.entities.Highlight

data class SportResponse(
    @SerializedName("highlights") val highlights: List<Highlight>
)