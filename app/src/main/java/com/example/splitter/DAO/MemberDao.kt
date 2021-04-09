package com.example.splitter.DAO

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.splitter.entities.Members
import com.example.splitter.entities.Trip

@Dao
interface MemberDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMember(member: Members)

    //Trying to get list of members with particular trip_id using this function
    @Query("Select * from Members where trip_id=:id")
    suspend fun getSelectedMembers(id: Int):List<Members>
    @Query("SELECT * FROM Members order by id asc ")
    fun getAllMembers() : LiveData<List<Members>>
    @Delete
    suspend fun deleteMember(member: Members)
    @Update
    suspend fun updateUser(member: Members)


}