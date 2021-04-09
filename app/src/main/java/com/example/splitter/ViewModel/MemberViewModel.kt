package com.example.splitter.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.splitter.Database.TripDatabase
import com.example.splitter.Repository.MemberRepositiry
import com.example.splitter.Repository.TripRepository
import com.example.splitter.entities.Members
import com.example.splitter.entities.Trip
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class MemberViewModel(application: Application): AndroidViewModel(application) {
    val allMembers: LiveData<List<Members>>

    val repo: MemberRepositiry
    init {
        val dao= TripDatabase.getDatabase(application).getMemberDao()
        repo= MemberRepositiry(dao)
        allMembers=repo.totalMembers

    }
    fun insertMember(member: Members)=viewModelScope.launch(Dispatchers.IO) {
        repo.insert(member)


    }
    fun deleteMember(member: Members)=viewModelScope.launch(Dispatchers.IO) {
        repo.deleteMember(member)


    }
    fun updateUser(member: Members)=viewModelScope.launch {
        repo.updateMember(member)
    }
    fun getAllMembers(id:Int):List<Members> = runBlocking{
         repo.getSelectedMembers(id)
    }


}