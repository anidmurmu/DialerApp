package com.example.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contact")
data class ContactEntity(
    @PrimaryKey
    @ColumnInfo(name = "contact_num")
    val contactNumber: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "is_blocked")
    val isBlocked: Boolean = false
)