package com.imgur.imgurapidemo.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.imgur.imgurapidemo.database.DatabaseImageDetails
import com.imgur.imgurapidemo.database.ImageBoundaryCallback
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

    fun InitialzeImageDetails():LiveData<PagedList<DatabaseImageDetails>> {
        val dataSourceFactory = database.imageDatabaseDao.getAllImageDetails()

        val boundaryCallback = ImageBoundaryCallback(this)

        return LivePagedListBuilder(dataSourceFactory, DATABASE_PAGE_SIZE)
            .setBoundaryCallback(boundaryCallback).build()
    }

    val imagedetailsList: LiveData<PagedList<DatabaseImageDetails>> = InitialzeImageDetails()

    /**
     * Refresh the ImageDetails stored in the offline cache.
     *
     * This function uses the IO dispatcher to ensure the database insert database operation
     * happens on the IO dispatcher. By switching to the IO dispatcher using `withContext` this
     * function is now safe to call from any thread including the Main thread.
     *
     * To actually load the ImageDetails for use, observe [ImageDetails]
     */
    suspend fun refreshImageDetails(page:Int) {
        withContext(Dispatchers.IO) {
            val imageDetailsList = ImagesAPI.retrofitService.getImagedetails(page).await()
            database.imageDatabaseDao.insert(*imageDetailsList.asDatabaseModel())
        }
    }

    suspend fun deleteNSFWImageDetails() {
        withContext(Dispatchers.IO) {

            database.imageDatabaseDao.deleteNSFWItems()
        }
    }

    companion object {

        private const val DATABASE_PAGE_SIZE = 20
    }
}
