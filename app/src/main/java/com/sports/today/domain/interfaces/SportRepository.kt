package com.sports.today.domain.interfaces

import com.sports.today.data.network.SportResponse

interface SportRepository {
    suspend fun getSports(): SportResponse
}