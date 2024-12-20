package com.twoonethree.CanopusDemo.koinsetup

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import com.twoonethree.CanopusDemo.localstorage.AppDatabase
import com.twoonethree.CanopusDemo.viewmodel.AuthViewModel
import com.twoonethree.CanopusDemo.viewmodel.RideViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


val koinmodule = module {

    single {
        Room.databaseBuilder(get(), AppDatabase::class.java, "app_database").build()
    }

    single {
        get<AppDatabase>().userDao()
    }

    single {
        get<AppDatabase>().rideDao()
    }

    viewModel { AuthViewModel(get()) }
    viewModel { RideViewModel(get()) }

}