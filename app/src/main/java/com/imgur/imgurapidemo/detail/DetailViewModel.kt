package com.imgur.imgurapidemo.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.imgur.imgurapidemo.network.ImageDetails

class DetailViewModel(imageDetails: ImageDetails, app: Application) : AndroidViewModel(app) {
    private val _selectedImage = MutableLiveData<ImageDetails>()

    // The external LiveData for the SelectedImage
    val selectedImage: LiveData<ImageDetails>
        get() = _selectedImage

    // Initialize the _selectedImage MutableLiveData
    init {
        _selectedImage.value = imageDetails
    }



}