package com.twoonethree.CanopusDemo.localstorage

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey val email: String,
    val password: String
)

@Entity
data class Ride(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val carType: String,
    val distance: Float,
    val fare: Float,
    val dateTime: String
)