package com.twoonethree.CanopusDemo

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.twoonethree.CanopusDemo.screens.AuthScreen
import com.twoonethree.CanopusDemo.screens.HomeScreen
import com.twoonethree.CanopusDemo.screens.RideHistoryScreen
import com.twoonethree.CanopusDemo.screens.RideScreen

@Composable
fun NavigationSetup()
{
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login")
    {
        composable("login")
        {
            AuthScreen(navController = navController)
        }
        composable("home")
        {
            HomeScreen(navController = navController)
        }
        composable("ride")
        {
            RideScreen()
        }
        composable("ride_history")
        {
            RideHistoryScreen()
        }
    }

}