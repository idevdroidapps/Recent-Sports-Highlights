package com.sports.today.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sports.today.domain.entities.Highlight
import com.sports.today.domain.usecases.SportUseCases
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SharedViewModel(private val sportUseCases: SportUseCases) : ViewModel() {

    val highlights: LiveData<List<Highlight>> get() = _highlights
    private var _highlights = MutableLiveData<List<Highlight>>()

    var selectedHighlight: Highlight? = null

    val shouldShowDetails: LiveData<Boolean> get() = _shouldShowDetails
    private var _shouldShowDetails = MutableLiveData<Boolean>()

    init {
        viewModelScope.launch(Dispatchers.IO){
            val response = sportUseCases.getHighlights()
            _highlights.value = response.highlights
        }
    }

    fun showDetails(show: Boolean) {
        _shouldShowDetails.value = show
    }

}