package com.example.splitter.DAO

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.splitter.entities.Trip
import kotlinx.coroutines.flow.Flow

@Dao
interface TripDao {


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUsers(trip:Trip)
    @Query("SELECT * FROM Trip order by tid ASC")
    fun getAllTrips() : LiveData<List<Trip>>
    @Query("SELECT * FROM trip WHERE trip_name LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): Flow<List<Trip>>
    @Delete
    suspend fun deleteTrip(trip:Trip)



}