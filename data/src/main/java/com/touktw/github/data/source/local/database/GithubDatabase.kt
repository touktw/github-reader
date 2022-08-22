package com.touktw.github.data.source.local.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.touktw.github.data.BuildConfig

abstract class GithubDatabase : RoomDatabase() {
}

private lateinit var instance: GithubDatabase
private const val DB_NAME = "github"

fun database(context: Context): GithubDatabase {
    if (!::instance.isInitialized) {
        val builder = Room.databaseBuilder(
            context.applicationContext,
            GithubDatabase::class.java,
            DB_NAME
        )
        if (BuildConfig.DEBUG) {
            builder.fallbackToDestructiveMigration()
        }
        instance = builder.build()
    }
    return instance
}