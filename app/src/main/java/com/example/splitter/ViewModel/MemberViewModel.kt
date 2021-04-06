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

//yahan bhi id parameter pass kiye h
class MemberViewModel(application: Application,id:Int): AndroidViewModel(application) {
    val allMembers: LiveData<List<Members>>
    val repo: MemberRepositiry
    init {
        val dao= TripDatabase.getDatabase(application).getMemberDao()
        repo= MemberRepositiry(dao,id)
        allMembers=repo.totalMembers
    }
    fun insertMember(member: Members)=viewModelScope.launch(Dispatchers.IO) {
        repo.insert(member)


    }

}