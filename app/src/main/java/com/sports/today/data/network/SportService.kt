package com.sports.today.data.network

import retrofit2.http.POST

interface SportService {

    @POST("results")
    suspend fun getHighlights(): SportResponse

}