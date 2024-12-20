package com.twoonethree.CanopusDemo.localstorage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.twoonethree.CanopusDemo.localstorage.Ride

@Dao
interface RideDao {
    @Insert
    suspend fun addRide(ride: Ride)

    @Query("SELECT * FROM Ride")
    fun getAllRides(): List<Ride>
}