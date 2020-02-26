package com.imgur.imgurapidemo.network

import com.imgur.imgurapidemo.database.DatabaseImageDetails
import com.imgur.imgurapidemo.domain.ImageDetails
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkImageDetailsContainer(val imageDetails: List<NetworkImageDetails>)

/**
 * ImageDetails.
 */
@JsonClass(generateAdapter = true)
data class NetworkImageDetails(
    var imageId: Long = 0L,

    val id: String,

    val link: String,

    val nsfw:Boolean,

    val title:String,

    val views:Long,

    val votes:Long)

/**
 * Convert Network results to database objects
 */
fun NetworkImageDetailsContainer.asDomainModel(): List<ImageDetails> {
    return imageDetails.map {
        ImageDetails(
            imageId = it.imageId,
            id = it.id,
            link = it.link,
            nsfw = it.nsfw,
            title = it.title,
            views = it.views,
            votes = it.votes
        )
    }
}

fun NetworkImageDetailsContainer.asDatabaseModel(): Array<DatabaseImageDetails> {
    return imageDetails.map {
        DatabaseImageDetails(
            imageId = it.imageId,
            id = it.id,
            link = it.link,
            nsfw = it.nsfw,
            title = it.title,
            views = it.views,
            votes = it.votes)
    }.toTypedArray()
}
