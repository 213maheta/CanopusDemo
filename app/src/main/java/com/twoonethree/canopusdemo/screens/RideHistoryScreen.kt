package com.twoonethree.CanopusDemo.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.room.*
import com.twoonethree.CanopusDemo.localstorage.Ride
import com.twoonethree.CanopusDemo.viewmodel.RideViewModel
import org.koin.androidx.compose.koinViewModel
import java.util.*

@Composable
fun RideHistoryScreen(rideViewModel: RideViewModel = koinViewModel()) {

    LaunchedEffect(key1 = Unit) {
        rideViewModel.loadRides()
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Ride History", style = MaterialTheme.typography.headlineMedium, modifier = Modifier.padding(bottom = 8.dp))

        if (rideViewModel.rides.isEmpty()) {
            Text("No rides found.", style = MaterialTheme.typography.bodyMedium)
        } else {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(rideViewModel.rides) { ride ->
                    RideHistoryItem(ride)
                }
            }
        }
    }
}

@Composable
fun RideHistoryItem(ride: Ride) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Car Type: ${ride.carType}", style = MaterialTheme.typography.bodyMedium)
            Text("Distance: ${ride.distance} km", style = MaterialTheme.typography.bodyMedium)
            Text("Fare: $${ride.fare}", style = MaterialTheme.typography.bodyMedium)
            Text("Date: ${ride.dateTime}", style = MaterialTheme.typography.bodyMedium)
        }
    }
}
