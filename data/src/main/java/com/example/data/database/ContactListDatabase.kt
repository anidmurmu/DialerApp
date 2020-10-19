package com.example.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.database.dao.ContactDao
import com.example.data.database.model.ContactEntity
import kotlinx.coroutines.CoroutineScope

const val DATABASE_NAME = "contact_list_database"

@Database(entities = [ContactEntity::class], version = 1, exportSchema = false)
abstract class ContactListDatabase : RoomDatabase() {

  abstract fun getContactDao(): ContactDao

  /*companion object {
    @Volatile
    private var INSTANCE: ContactListDatabase? = null
    fun getDatabase(scope: CoroutineScope, context: Context): ContactListDatabase? {
      if (INSTANCE == null) {
        synchronized(this) {
          val instance = Room.databaseBuilder(
              context.applicationContext,
              ContactListDatabase::class.java,
              DATABASE_NAME
          ).build()
          INSTANCE = instance
        }
      }
      return INSTANCE
    }
  }*/
}