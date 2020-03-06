package com.imgur.imgurapidemo.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.imgur.imgurapidemo.repository.ImagesRepository
import kotlinx.coroutines.Dispatchers.Unconfined
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class ImageBoundaryCallback(

    private val repository: ImagesRepository
):PagedList.BoundaryCallback<DatabaseImageDetails>() {

    private var lastRequestedPage = 1

    private val _networkErrors = MutableLiveData<String>()
    // LiveData of network errors.
    val networkErrors: LiveData<String>
        get() = _networkErrors

    // avoid triggering multiple requests in the same time
    private var isRequestInProgress = false

    override fun onZeroItemsLoaded() {

        runBlocking {
            launch(Unconfined) {
                repository.refreshImageDetails(lastRequestedPage)
            }
        }

    }

    override fun onItemAtEndLoaded(itemAtEnd: DatabaseImageDetails) {
        runBlocking {
            launch(Unconfined) {
                repository.refreshImageDetails(lastRequestedPage++)
            }
        }
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 50
    }
}