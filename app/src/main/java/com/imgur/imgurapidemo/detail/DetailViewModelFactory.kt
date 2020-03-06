package com.imgur.imgurapidemo.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.imgur.imgurapidemo.database.DatabaseImageDetails


class DetailViewModelFactory(
    private val imageDetails: DatabaseImageDetails,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(imageDetails, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}