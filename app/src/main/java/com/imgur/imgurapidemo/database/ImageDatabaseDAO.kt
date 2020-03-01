package com.imgur.imgurapidemo.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.imgur.imgurapidemo.domain.ImageDetails

@Dao
interface ImageDatabaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg imageDetails: DatabaseImageDetails)



    /**
     * When updating a row with a value already set in a column,
     * replaces the old value with the new one.
     *
     * @param DatabaseImageDetails new value to write
     */
    @Update
    fun update(imageDetails: DatabaseImageDetails)

    /**
     * Selects and returns the row that matches
     */
    @Query("SELECT * from DatabaseImageDetails WHERE id = :key")
    fun get(key: Long): DatabaseImageDetails?

    /**
     * Deletes all values from the table.
     *
     * This does not delete the table, only its contents.
     */
    @Query("DELETE FROM DatabaseImageDetails")
    fun clear()

    /**
     * Selects and returns all rows in the table,
     *
     * sorted in descending order.
     */
    @Query("SELECT * FROM DatabaseImageDetails")
    fun getAllImageDetails(): LiveData<List<DatabaseImageDetails>>



    /**
     * Selects and returns the image with given id.
     */
    @Query("SELECT * from DatabaseImageDetails WHERE id = :key")
    fun getImageWithId(key: Long): LiveData<DatabaseImageDetails>
}

