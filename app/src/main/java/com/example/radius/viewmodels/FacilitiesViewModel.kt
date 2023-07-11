package com.example.radius.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.radius.models.ResponseData
import com.example.radius.network.RadiusApi
import kotlinx.coroutines.launch

class FacilitiesViewModel : ViewModel() {

    // Internally, we use a MutableLiveData, because we will be updating the List of MarsPhoto
    // with new values
    private val _facilitiesData = MutableLiveData<ResponseData>()

    // The external LiveData interface to the property is immutable, so only this class can modify
    val facilitiesData: LiveData<ResponseData> = _facilitiesData

    /**
     * Call getFacilitiesData() on init so we can display status immediately.
     */
    init {
        getFacilitiesData()
    }

    private fun getFacilitiesData() {

        viewModelScope.launch {
            try {
                _facilitiesData.value = RadiusApi.retrofitService.getFacilitiesData()
            } catch (e: Exception) {
                Log.e("Dummy",e.printStackTrace().toString())
                e.printStackTrace()

                _facilitiesData.value = ResponseData()
            }
        }
    }
}