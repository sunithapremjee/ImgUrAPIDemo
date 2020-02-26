package com.imgur.imgurapidemo.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.imgur.imgurapidemo.domain.ImageDetails
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class DatabaseImageDetails constructor(
    @PrimaryKey
    var id: Long = 0L,
    @ColumnInfo(name = "imageid")
    val imageId: String,
    @ColumnInfo(name = "imageURL")
    val imgSrcUrl: String,

    val nsfw:Boolean): Parcelable {

}

fun List<DatabaseImageDetails>.asDomainModel(): List<ImageDetails> {
    return map {
        ImageDetails(
            id = it.id,
            imageId = it.imageId,
            imgSrcUrl = it.imgSrcUrl,
            nsfw = it.nsfw
            )
    }
}