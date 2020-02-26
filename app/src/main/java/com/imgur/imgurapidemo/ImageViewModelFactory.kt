package com.imgur.imgurapidemo

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.imgur.imgurapidemo.database.ImageDatabase
import com.imgur.imgurapidemo.database.ImageDatabaseDao

class ImageViewModelFactory(
    private val database: ImageDatabase,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ImageViewViewModel::class.java)) {
            return ImageViewViewModel(database, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}