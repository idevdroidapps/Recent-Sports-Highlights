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
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

class SharedViewModel(private val sportUseCases: SportUseCases) : ViewModel() {

    val f1Results: LiveData<List<Formula1>> get() = _f1Results
    private var _f1Results = MutableLiveData<List<Formula1>>()

    val basketballResults: LiveData<List<Basketball>> get() = _basketballResults
    private var _basketballResults = MutableLiveData<List<Basketball>>()

    val tennisResults: LiveData<List<Tennis>> get() = _tennisResults
    private var _tennisResults = MutableLiveData<List<Tennis>>()

    val shouldNavToDetails: LiveData<Boolean> get() = _shouldNavToDetails
    private var _shouldNavToDetails = MutableLiveData<Boolean>()

    val retrievalError: LiveData<Boolean> get() = _retrievalError
    private var _retrievalError = MutableLiveData<Boolean>()

    var selectedText: String? = null

    private val initDayString = "Jan 1, 1971 1:00:01 AM"
    private var mostRecentDay =
        LocalDateTime.parse(initDayString, DateTimeFormatter.ofPattern(DATE_PATTERN))

    fun fetchSports() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = sportUseCases.getSports()

                val basketballResults = response.nbaResults.sortedWith(compareByDescending {
                    convertTime(it.publicationDate)
                })
                val formula1Results = response.f1Results.sortedWith(compareByDescending {
                    convertTime(it.publicationDate)
                })
                val tennisResults = response.tennisResults.sortedWith(compareByDescending {
                    convertTime(it.publicationDate)
                })

                val filteredBasketball = basketballResults.filter {
                    isSameDay(it.publicationDate)
                }
                val filteredFormula1 = formula1Results.filter{
                    isSameDay(it.publicationDate)
                }
                val filteredTennis = tennisResults.filter{
                    isSameDay(it.publicationDate)
                }

                _f1Results.postValue(filteredFormula1)
                _basketballResults.postValue(filteredBasketball)
                _tennisResults.postValue(filteredTennis)

            } catch (e: IOException) {
                Log.e("OkHttp", e.toString())
                _retrievalError.value = true
            } catch (e: HttpException) {
                Log.e("OkHttp", e.toString())
                _retrievalError.value = true
            }
        }
    }

    fun navToDetails(nav: Boolean) {
        _shouldNavToDetails.value = nav
    }

    fun resetOnError(hide: Boolean) {
        _retrievalError.value = hide
    }

    private fun convertTime(dateString: String): Long {
        val ldt = LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern(DATE_PATTERN))
        mostRecentDay = maxOf(mostRecentDay, ldt)

        val date = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant())
        return date.time
    }

    private fun isSameDay(dateString: String): Boolean{
        val ldt = LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern(DATE_PATTERN))
        return ldt.year == mostRecentDay.year && ldt.dayOfYear == mostRecentDay.dayOfYear
    }

    companion object {
        private const val DATE_PATTERN = "MMM d, yyyy h:mm:ss a"
    }

}