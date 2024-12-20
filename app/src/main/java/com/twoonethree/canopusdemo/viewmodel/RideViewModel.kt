package com.twoonethree.CanopusDemo.viewmodel

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.geometry.times
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.*
import com.twoonethree.CanopusDemo.localstorage.Ride
import com.twoonethree.CanopusDemo.localstorage.dao.RideDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class RideViewModel(private val rideDao: RideDao) : ViewModel() {
    var message by mutableStateOf("")

    var rides by mutableStateOf<List<Ride>>(emptyList())

    fun loadRides() {
        viewModelScope.launch(Dispatchers.IO) {
            rides = rideDao.getAllRides()
        }
    }

    private val carTypeFares = mapOf(
        "Sedan" to 10f,
        "SUV" to 15f,
        "Hatchback" to 8f
    )

    fun calculateFare(distance: String, selectedCarType: String): Float {
        val dist = distance.toFloatOrNull() ?: 0f
        return (carTypeFares[selectedCarType] ?: 0f) * dist
    }

    fun bookRide(distance: String, selectedCarType: String, fare: Float) {
        val dist = distance.toFloatOrNull()
        if (selectedCarType.isBlank() || dist == null || dist <= 0) {
            message = "Please select a valid car type and distance."
            return
        }

        val dateTime = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())

        viewModelScope.launch {
            rideDao.addRide(Ride(carType = selectedCarType, distance = dist, fare = fare, dateTime = dateTime))
            message = "Ride booked successfully!"
        }
    }
}