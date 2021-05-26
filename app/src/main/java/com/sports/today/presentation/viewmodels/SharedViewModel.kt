package com.sports.today.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sports.today.domain.entities.Basketball
import com.sports.today.domain.entities.Formula1
import com.sports.today.domain.entities.Tennis
import com.sports.today.domain.usecases.SportUseCases
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException

class SharedViewModel(private val sportUseCases: SportUseCases) : ViewModel() {

    val f1Results: LiveData<List<Formula1>> get() = _f1Results
    private var _f1Results = MutableLiveData<List<Formula1>>()

    val basketballResults: LiveData<List<Basketball>> get() = _basketballResults
    private var _basketballResults = MutableLiveData<List<Basketball>>()

    val tennisResults: LiveData<List<Tennis>> get() = _tennisResults
    private var _tennisResults = MutableLiveData<List<Tennis>>()

    val shouldNavToDetails: LiveData<Boolean> get() = _shouldNavToDetails
    private var _shouldNavToDetails = MutableLiveData<Boolean>()

    var selectedDrawableId: Int? = null
    var selectedText: String? = null

    init {
        viewModelScope.launch(Dispatchers.IO){
            try {

                val response = sportUseCases.getSports()

                _f1Results.postValue(response.f1Results)
                _basketballResults.postValue(response.nbaResults)
                _tennisResults.postValue(response.tennisResults)

            } catch (e: IOException) {
                Log.e("OkHttp", e.toString())
            } catch (e: HttpException){
                Log.e("OkHttp", e.toString())
            }
        }
    }

    fun navToDetails(nav: Boolean) {
        _shouldNavToDetails.value = nav
    }

}