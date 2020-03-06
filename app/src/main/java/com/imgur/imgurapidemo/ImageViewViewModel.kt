package com.imgur.imgurapidemo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.imgur.imgurapidemo.database.DatabaseImageDetails
import com.imgur.imgurapidemo.database.ImageDatabase
import com.imgur.imgurapidemo.database.ImageDatabaseDao
import com.imgur.imgurapidemo.domain.ImageDetails
import com.imgur.imgurapidemo.network.ImagesAPI
import com.imgur.imgurapidemo.repository.ImagesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber

class ImageViewViewModel(
    database:ImageDatabase,
    application: Application
) : AndroidViewModel(application) {

    val TAG:String = "ImageViewViewModel"



    private val _navigateToSelectedImage = MutableLiveData<DatabaseImageDetails>()

    val navigateToSelectedImage: LiveData<DatabaseImageDetails>
        get() = _navigateToSelectedImage

    // Create a Coroutine scope using a job to be able to cancel when needed
    private var viewModelJob = Job()

    // the Coroutine runs using the Main (UI) dispatcher
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)


    private val imagesRepository = ImagesRepository(database)

    /**
     * init{} is called immediately when this ViewModel is created.
     */
   /* init {
        refreshImageDetails()
    }*/

    init {
        imagesRepository.InitialzeImageDetails()
    }
    val imagedetailsList = imagesRepository.imagedetailsList

    /**
     * When the [ViewModel] is finished, we cancel our coroutine [viewModelJob], which tells the
     * Retrofit service to stop.
     */
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    /**
     * When the image is clicked, set the [_navigateToSelectedImage] [MutableLiveData]
     * @param imagedetails The [ImageDetails] that was clicked on.
     */
    fun displayImageDetails(imagedetails: DatabaseImageDetails) {
        _navigateToSelectedImage.value = imagedetails
    }

    /**
     * After the navigation has taken place, make sure navigateToSelectedImage is set to null
     */
    fun displayImageDetailsComplete() {
        _navigateToSelectedImage.value = null
    }

    fun deleteNSFW()
    {
        coroutineScope.launch {
            imagesRepository.deleteNSFWImageDetails()

        }
    }
   /* fun refreshImageDetails()
    {
        coroutineScope.launch {
            imagesRepository.refreshImageDetails()
        }
    }*/
}
