package com.sports.today.presentation.injection

import androidx.lifecycle.ViewModelProvider
import com.sports.today.data.network.SportApi
import com.sports.today.data.network.SportService
import com.sports.today.data.repositories.SportRepositoryImpl
import com.sports.today.domain.interfaces.SportRepository
import com.sports.today.domain.usecases.SportUseCases
import com.sports.today.presentation.factories.SharedViewModelFactory

object DependencyInject {

    /**
     * Provides Retrofit Service
     */
    private fun provideSportService(): SportService {
        return SportApi.sportService
    }

    /**
     * Provides a single source of truth ViewModels
     */

    private fun provideSportRepository(): SportRepository {
        return SportRepositoryImpl.getInstance(provideSportService())
    }

    /**
     * Provides an instance of [SportUseCases]
     */
    private fun provideSportUseCases(): SportUseCases {
        return SportUseCases(provideSportRepository())
    }

    /**
     * Provides the [ViewModelProvider.Factory]
     */
    fun provideSharedViewModelFactory(): ViewModelProvider.Factory {
        return SharedViewModelFactory(provideSportUseCases())
    }
}