package com.imgur.imgurapidemo.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.imgur.imgurapidemo.database.DatabaseImageDetails


class DetailViewModel(imageDetails: DatabaseImageDetails, app: Application) : AndroidViewModel(app) {
    private val _selectedImage = MutableLiveData<DatabaseImageDetails>()

    // The external LiveData for the SelectedImage
    val selectedImage :LiveData<DatabaseImageDetails?>
        get() = _selectedImage

    // Initialize the _selectedImage MutableLiveData
    init {
        _selectedImage.value = imageDetails
    }



}