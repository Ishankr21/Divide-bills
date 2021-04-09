package com.example.splitter.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.splitter.Database.TripDatabase
import com.example.splitter.Repository.TripRepository
import com.example.splitter.entities.Members
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
    fun deleteTrip(trip:Trip)=viewModelScope.launch(Dispatchers.IO) {
        repo.deleteTrip(trip)


    }
    fun searchDatabase(searchQuery: String): LiveData<List<Trip>> {
        return repo.searchDatabase(searchQuery).asLiveData()
    }

}