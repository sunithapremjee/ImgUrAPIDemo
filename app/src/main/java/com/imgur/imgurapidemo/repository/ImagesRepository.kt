package com.imgur.imgurapidemo.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.imgur.imgurapidemo.database.ImageDatabase
import com.imgur.imgurapidemo.database.asDomainModel
import com.imgur.imgurapidemo.domain.ImageDetails
import com.imgur.imgurapidemo.network.ImagesAPI
import com.imgur.imgurapidemo.network.asDatabaseModel
import com.imgur.imgurapidemo.network.asDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ImagesRepository(private val database: ImageDatabase) {

    /**
     * Images that can be shown on the screen.
     */
    val imagedetailsList: LiveData<List<ImageDetails>> =
        Transformations.map(database.imageDatabaseDao.getAllImageDetails()) {
            it.asDomainModel()
        }

    /**
     * Refresh the ImageDetails stored in the offline cache.
     *
     * This function uses the IO dispatcher to ensure the database insert database operation
     * happens on the IO dispatcher. By switching to the IO dispatcher using `withContext` this
     * function is now safe to call from any thread including the Main thread.
     *
     * To actually load the ImageDetails for use, observe [ImageDetails]
     */
    suspend fun refreshImageDetails() {
        withContext(Dispatchers.IO) {
            val imageDetailsList = ImagesAPI.retrofitService.getImagedetails().await()
            database.imageDatabaseDao.insert(*imageDetailsList.asDatabaseModel())
        }
    }

    suspend fun deleteNSFWImageDetails() {
        withContext(Dispatchers.IO) {

            database.imageDatabaseDao.deleteNSFWItems()
        }
    }
}
