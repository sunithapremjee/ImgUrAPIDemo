package com.imgur.imgurapidemo.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ImageDatabaseDao {

    @Insert
    fun insert(imageDetails: ImageDetails)



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(imageDetails: List<ImageDetails>?)

    /**
     * When updating a row with a value already set in a column,
     * replaces the old value with the new one.
     *
     * @param ImageDetails new value to write
     */
    @Update
    fun update(imageDetails: ImageDetails)

    /**
     * Selects and returns the row that matches
     */
    @Query("SELECT * from image_details_table WHERE id = :key")
    fun get(key: Long): ImageDetails?

    /**
     * Deletes all values from the table.
     *
     * This does not delete the table, only its contents.
     */
    @Query("DELETE FROM image_details_table")
    fun clear()

    /**
     * Selects and returns all rows in the table,
     *
     * sorted in descending order.
     */
    @Query("SELECT * FROM image_details_table ORDER BY id DESC")
    fun getAllNights(): LiveData<List<ImageDetails>>



    /**
     * Selects and returns the image with given id.
     */
    @Query("SELECT * from image_details_table WHERE id = :key")
    fun getImageWithId(key: Long): LiveData<ImageDetails>
}

