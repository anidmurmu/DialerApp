package com.example.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.data.database.model.ContactEntity


@Dao
interface ContactDao {
  @Query("SELECT * FROM contact WHERE is_blocked = 'false' ORDER BY full_name ASC")
  fun getContactList(): LiveData<List<ContactEntity>>

  @Query("SELECT * FROM contact WHERE is_blocked = 'true' ORDER BY full_name ASC")
  fun getBlockedContactList(): LiveData<List<ContactEntity>>

  @Insert
  fun addContact(contactEntity: ContactEntity)

  @Update
  fun updateContact(contactEntity: ContactEntity)

  @Delete
  fun deleteContact(contactEntity: ContactEntity)

  @Update
  fun blockContact(contactEntity: ContactEntity)
}