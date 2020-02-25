package com.imgur.imgurapidemo.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "image_details_table")
@Parcelize
data class ImageDetails(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    @ColumnInfo(name = "imageid")
    @Json(name = "id")
    val imageId: String,
    // used to map link from the JSON to imgSrcUrl in our class
    @ColumnInfo(name = "imageURL")
    @Json(name = "link")
    val imgSrcUrl: String,
    @Json(name = "nsfw")
    val nsfw:Boolean
    ) : Parcelable {

}
