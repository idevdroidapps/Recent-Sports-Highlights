package com.sports.today.data.network

import retrofit2.http.Headers
import retrofit2.http.POST

interface SportService {

    @Headers("Content-Type:application/json; charset=UTF-8")
    @POST("results")
    suspend fun getSports(): SportResponse

}