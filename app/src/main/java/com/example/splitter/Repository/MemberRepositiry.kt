package com.example.splitter.Repository

import androidx.lifecycle.LiveData
import com.example.splitter.DAO.MemberDao

import com.example.splitter.entities.Members


//Yahan pe id parameter pass kiye h
class MemberRepositiry(private val Memberdao: MemberDao, id:Int) {
    val totalMembers: LiveData<List<Members>> =Memberdao.getAllMembers(id)
    suspend fun insert(member: Members)
    {
        Memberdao.insertMember(member)
    }

}