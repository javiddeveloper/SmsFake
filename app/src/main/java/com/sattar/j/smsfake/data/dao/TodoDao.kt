package com.sattar.j.smsfake.data.dao

import androidx.room.*
import com.sattar.j.smsfake.data.entity.Destination

/**
 * @author : javid
 * @summary : --
 * @since : 2020/Aug -- 5:55 PM
 */
@Dao
interface TodoDao {

    @Query("SELECT * FROM destination")
    fun getAllList(): List<Destination>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addDestination(destination: Destination)

    @Delete
    fun deleteDestination(destination: Destination)

    @Update
    fun updateDestination(destination: Destination)
}