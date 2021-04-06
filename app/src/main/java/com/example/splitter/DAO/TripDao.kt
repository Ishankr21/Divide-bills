package com.example.splitter.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.splitter.entities.Trip

@Dao
interface TripDao {


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUsers(trip:Trip)
    @Query("SELECT * FROM Trip ORDER BY tid ASC")
    fun getAllTrips() : LiveData<List<Trip>>


}