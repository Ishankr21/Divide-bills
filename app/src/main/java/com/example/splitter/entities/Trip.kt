package com.example.splitter.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class Trip(
    @PrimaryKey(autoGenerate = true) var tid: Int,
    @ColumnInfo(name = "trip_name") var TripName: String?,

    ):Parcelable