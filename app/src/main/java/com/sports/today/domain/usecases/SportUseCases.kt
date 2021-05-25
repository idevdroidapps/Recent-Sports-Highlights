package com.sports.today.domain.usecases

import com.sports.today.data.network.SportResponse
import com.sports.today.domain.interfaces.SportRepository

class SportUseCases(private val sportRepository: SportRepository) {

    suspend fun getSports(): SportResponse {
        return sportRepository.getSports()
    }
}