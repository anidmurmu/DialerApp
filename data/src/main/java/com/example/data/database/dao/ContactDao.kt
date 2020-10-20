package com.example.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.data.database.model.ContactEntity


@Dao
interface ContactDao {
  @Query("SELECT * FROM contact WHERE is_blocked = :isBlocked ORDER BY full_name ASC")
  suspend fun getContactList(isBlocked: Boolean): List<ContactEntity>

  @Query("SELECT * FROM contact WHERE is_blocked =:isBlocked ORDER BY full_name ASC")
  //fun getBlockedContactList(): LiveData<List<ContactEntity>>
  fun getBlockedContactList(isBlocked: Boolean): List<ContactEntity>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun addContact(contactEntity: ContactEntity)

  @Update
  suspend fun updateContact(contactEntity: ContactEntity)

  @Delete
  suspend fun deleteContact(contactEntity: ContactEntity)

  @Update
  suspend fun blockContact(contactEntity: ContactEntity)

  @Update
  suspend fun unblockContact(contactEntity: ContactEntity)
}