package com.twoonethree.CanopusDemo.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController)
{
    val mode = remember {
        mutableStateOf(0)
    }

    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center){
        Box(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.95f))
        {
            if(mode.value == 0 )
            {
                RideScreen()
            }
            else
            {
                RideHistoryScreen()
            }
        }

        Row(modifier = Modifier.fillMaxSize())
        {
            Box(modifier = Modifier
                .fillMaxWidth(0.5f)
                .fillMaxHeight()
                .clickable {
                    mode.value = 0
                }
                .border(width = 2.dp, color = Color.Black)
            )
            {
                Text(text = "Rides", fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.align(Alignment.Center)

                )
            }

            Box(modifier = Modifier
                .fillMaxSize()
                .clickable {
                    mode.value = 1
                }
                .border(width = 2.dp, color = Color.Black)
            )
            {
                Text(text = "Rides History",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.align(Alignment.Center)

                )
            }
        }
    }
}