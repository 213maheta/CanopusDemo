package com.twoonethree.CanopusDemo.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.room.*
import com.twoonethree.CanopusDemo.viewmodel.RideViewModel
import org.koin.androidx.compose.koinViewModel
import java.util.*

@Composable
fun RideScreen(rideViewModel: RideViewModel = koinViewModel()) {
    val carTypes = listOf("Sedan", "SUV", "Hatchback")
    var selectedCarType by remember { mutableStateOf("") }
    var distance by remember { mutableStateOf("") }
    var fare by remember { mutableStateOf(0f) }
    var expand by remember { mutableStateOf(false) }
    val context = LocalContext.current

    LaunchedEffect(key1 = rideViewModel.message) {
        if(rideViewModel.message.isNotEmpty())
        {
            Toast.makeText(context, rideViewModel.message, Toast.LENGTH_SHORT).show()
            rideViewModel.message = ""
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Row(modifier = Modifier.
        clickable { expand = !expand }) {
            Text(if(selectedCarType.isNotEmpty()) selectedCarType else "Select Car Type",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,)


            Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "")
        }

        Spacer(modifier = Modifier.height(8.dp))

        if(expand)
        {
            LazyColumn {
                items(carTypes)
                {
                    Text(text = it, modifier = Modifier
                        .clickable { selectedCarType = it }
                        .fillMaxWidth()
                        .border(
                            2.dp, androidx.compose.ui.graphics.Color.Black
                        )
                        .padding(16.dp)
                    )
                }
            }
        }

        TextField(
            value = distance,
            onValueChange = { distance = it },
            label = { Text("Distance (in km)") },
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = {
                fare = rideViewModel.calculateFare(distance, selectedCarType)
                Log.e("TAG", "RideScreen: $fare", )
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Calculate Fare")
        }
        Text(text = "Fare: $fare")
        Button(
            onClick = { rideViewModel.bookRide(distance, selectedCarType, fare) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Book Ride")
        }
    }
}
