package com.sattar.j.smsfake.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author : UserNumber
 * @summary : --
 * @since : 2020/Aug -- 4:32 PM
 */
@Entity(tableName = "userNumber")
data class UserMessage(
        @PrimaryKey(autoGenerate = true) var id: Int = 0,
        @ColumnInfo(name = "name") val name: String,
        @ColumnInfo(name = "number") val number: String,
        @ColumnInfo(name = "message") val message: String,
        @ColumnInfo(name = "avatar") val avatar: String
)