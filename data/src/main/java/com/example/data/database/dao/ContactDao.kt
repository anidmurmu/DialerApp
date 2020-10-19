package com.example.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.data.database.model.ContactEntity


@Dao
interface ContactDao {
  @Query("SELECT * FROM contact WHERE is_blocked = 'false'")
  fun getContactList(): LiveData<List<ContactEntity>>
}