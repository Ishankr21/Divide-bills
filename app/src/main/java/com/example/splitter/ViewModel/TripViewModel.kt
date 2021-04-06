package com.example.splitter.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.splitter.Database.TripDatabase
import com.example.splitter.Repository.TripRepository
import com.example.splitter.entities.Trip
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TripViewModel(application: Application): AndroidViewModel(application) {
    val alltrips: LiveData<List<Trip>>
    val repo: TripRepository
    init {
        val dao= TripDatabase.getDatabase(application).getTripDao()
        repo= TripRepository(dao)
        alltrips=repo.totalTrips
    }
    fun insertTrip(trip:Trip)=viewModelScope.launch(Dispatchers.IO) {
        repo.insert(trip)


    }

}