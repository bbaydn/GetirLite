package com.getir.patika.getirlite.data.entity

import android.content.Context
import androidx.room.Room

object DatabaseClient {
    private var instance: AppDatabase? = null

    fun getDatabase(context: Context): AppDatabase {
        if (instance == null) {
            synchronized(AppDatabase::class) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java, "getirlite_database"
                ).build()
            }
        }
        return instance!!
    }
}
