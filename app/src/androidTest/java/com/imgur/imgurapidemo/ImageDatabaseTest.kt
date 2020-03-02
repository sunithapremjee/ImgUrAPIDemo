package com.imgur.imgurapidemo

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.imgur.imgurapidemo.database.DatabaseImageDetails
import com.imgur.imgurapidemo.database.ImageDatabase
import com.imgur.imgurapidemo.database.ImageDatabaseDao
import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class ImageDatabaseTest{

    private lateinit var imageDao: ImageDatabaseDao
    private lateinit var db: ImageDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        db = Room.inMemoryDatabaseBuilder(context, ImageDatabase::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        imageDao = db.imageDatabaseDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetImageDetails() {
        val imageDetails = DatabaseImageDetails("TestId")
        imageDao.insert(imageDetails)
        val imageDetailsList = imageDao.getAllImageDetails()
        assertEquals(imageDetails?.id, "TestId")
    }
}