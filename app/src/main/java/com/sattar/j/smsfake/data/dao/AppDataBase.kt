package com.sattar.j.smsfake.data.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sattar.j.smsfake.data.entity.UserMessage

/**
 * @author : javid
 * @summary : --
 * @since : 2020/Aug -- 7:11 PM
 */
@Database(entities = [UserMessage::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun dao(): TodoDao
    companion object {
        @Volatile
        private var instance: AppDataBase? = null
        fun getInstance(context: Context): AppDataBase {
            return instance ?: synchronized(this) {
                instance ?: buildDB(context).also { instance = it }
            }
        }

        private fun buildDB(context: Context): AppDataBase =
                Room.databaseBuilder(context.applicationContext, AppDataBase::class.java, "appDB")
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()
    }
}