package com.imgur.imgurapidemo.domain

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize



data class ImageDetails(

    var id: Long = 0L,

    val imageId: String,

    val imgSrcUrl: String,

    val nsfw:Boolean
    )
