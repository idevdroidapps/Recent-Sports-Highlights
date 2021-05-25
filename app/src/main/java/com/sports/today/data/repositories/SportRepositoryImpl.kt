package com.sports.today.data.repositories

import com.sports.today.data.network.SportResponse
import com.sports.today.data.network.SportService
import com.sports.today.domain.interfaces.SportRepository

class SportRepositoryImpl(private val sportService: SportService) : SportRepository {

    override suspend fun getHighlights(): SportResponse {
        return sportService.getHighlights()
    }

    companion object {
        // For Singleton instantiation
        @Volatile
        private var instance: SportRepositoryImpl? = null
        fun getInstance(service: SportService) =
            instance ?: synchronized(this) {
                instance ?: SportRepositoryImpl(service).also { instance = it }
            }
    }

}