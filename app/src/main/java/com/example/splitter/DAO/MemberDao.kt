package com.example.splitter.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.splitter.entities.Members
import com.example.splitter.entities.Trip

@Dao
interface MemberDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMember(member: Members)

    //Trying to get list of members with particular trip_id using this function
    @Query("SELECT * FROM Members WHERE trip_id = :x")
    fun getAllMembers(x:Int) : LiveData<List<Members>>

}