package com.imgur.imgurapidemo.domain

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize


@Parcelize
data class ImageDetails(

     val id: String,

    val link: String,

    val nsfw:Boolean,

    val title:String,

    val views:Long,
    val vote:Long
    ): Parcelable {

}
