package com.example.splitter.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.splitter.DAO.MemberDao
import com.example.splitter.DAO.TripDao
import com.example.splitter.entities.Members
import com.example.splitter.entities.Trip

@Database(entities = arrayOf(Trip::class,Members::class), version = 1)
abstract class TripDatabase : RoomDatabase() {
    abstract fun getTripDao(): TripDao
    abstract  fun getMemberDao():MemberDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: TripDatabase? = null

        fun getDatabase(context: Context): TripDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TripDatabase::class.java,
                    "Trip_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}