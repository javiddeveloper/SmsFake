package com.sattar.j.smsfake.data.dao

import androidx.room.*
import com.sattar.j.smsfake.data.entity.UserMessage
import io.reactivex.Observable

/**
 * @author : javid
 * @summary : --
 * @since : 2020/Aug -- 5:55 PM
 */
@Dao
interface TodoDao {

    @Query("SELECT * FROM userNumber")
    fun getAllList(): List<UserMessage>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addDestination(userMessage: UserMessage)

    @Delete
    fun deleteDestination(userMessage: UserMessage)

    @Update
    fun updateDestination(userMessage: UserMessage)
}