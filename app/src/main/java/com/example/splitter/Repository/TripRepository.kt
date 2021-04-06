package com.example.splitter.Repository

import androidx.lifecycle.LiveData
import com.example.splitter.DAO.TripDao
import com.example.splitter.entities.Trip

class TripRepository(private val Tripdao: TripDao) {
    val totalTrips: LiveData<List<Trip>> =Tripdao.getAllTrips()
    suspend fun insert(trip: Trip)
    {
        Tripdao.insertUsers(trip)
    }

}