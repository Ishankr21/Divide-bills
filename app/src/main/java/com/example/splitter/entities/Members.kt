package com.example.splitter.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class Members(
    @PrimaryKey(autoGenerate = true) var id: Int,
    @ColumnInfo(name = "member_name") var MemberName: String?,
    @ColumnInfo(name = "trip_id") var kid: Int,
    @ColumnInfo(name="Spent") var spent:Double



):Parcelable
