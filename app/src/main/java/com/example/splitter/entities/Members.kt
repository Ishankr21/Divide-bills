package com.example.splitter.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Members(
    @PrimaryKey(autoGenerate = true) var id: Int,
    @ColumnInfo(name = "member_name") var MemberName: String?,
    @ColumnInfo(name = "trip_id") var kid: Int,


)
