package com.sports.today.presentation.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sports.today.domain.usecases.SportUseCases
import com.sports.today.presentation.viewmodels.SharedViewModel

class SharedViewModelFactory(private val sportUseCases: SportUseCases): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SharedViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SharedViewModel(sportUseCases) as T
        }
        throw IllegalArgumentException("Illegal ViewModel Class")
    }
}