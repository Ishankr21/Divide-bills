package com.example.splitter.Repository

import androidx.lifecycle.LiveData
import com.example.splitter.DAO.MemberDao

import com.example.splitter.entities.Members
import com.example.splitter.entities.Trip


//Yahan pe id parameter pass kiye h
class MemberRepositiry(private val Memberdao: MemberDao) {
    val totalMembers: LiveData<List<Members>> =Memberdao.getAllMembers()
    suspend fun insert(member: Members)
    {
        Memberdao.insertMember(member)
    }
    suspend fun getSelectedMembers(id:Int):List<Members>
    {
        return Memberdao.getSelectedMembers(id)
    }
    suspend fun deleteMember(member: Members)
    {
        Memberdao.deleteMember(member)
    }
    suspend fun updateMember(member: Members)
    {
        Memberdao.updateUser(member)
    }

}