package com.twoonethree.CanopusDemo.localstorage

import androidx.room.Database
import androidx.room.RoomDatabase
import com.twoonethree.CanopusDemo.localstorage.dao.RideDao
import com.twoonethree.CanopusDemo.localstorage.dao.UserDao

@Database(entities = [User::class, Ride::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun rideDao(): RideDao
}