package com.example.splitter.Repository

import androidx.lifecycle.LiveData
import com.example.splitter.DAO.TripDao
import com.example.splitter.entities.Trip
import kotlinx.coroutines.flow.Flow

class TripRepository(private val Tripdao: TripDao) {
    val totalTrips: LiveData<List<Trip>> =Tripdao.getAllTrips()
    suspend fun insert(trip: Trip)
    {
        Tripdao.insertUsers(trip)
    }
    fun searchDatabase(searchQuery: String): Flow<List<Trip>> {
        return Tripdao.searchDatabase(searchQuery)
    }
    suspend fun deleteTrip(trip:Trip)
    {
        Tripdao.deleteTrip(trip)
    }

}