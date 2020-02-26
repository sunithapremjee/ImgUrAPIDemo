package com.imgur.imgurapidemo.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.imgur.imgurapidemo.domain.ImageDetails
import kotlinx.android.parcel.Parcelize


@Entity
data class DatabaseImageDetails constructor(

    @PrimaryKey
    val id: String,
    @ColumnInfo(name = "link")
    val link: String,

    val nsfw:Boolean,
    val title:String,

    val views:Long,
    val vote:Long)

fun List<DatabaseImageDetails>.asDomainModel(): List<ImageDetails> {
    return map {
        ImageDetails(
            id = it.id,
            link = it.link,
            nsfw = it.nsfw,
            title = it.title,
            views = it.views,
            vote = it.vote
            )
    }
}