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
    var id: Long = 0L,

    val imageId: String,

    val imgSrcUrl: String,

    val nsfw:Boolean)

/**
 * Convert Network results to database objects
 */
fun NetworkImageDetailsContainer.asDomainModel(): List<ImageDetails> {
    return imageDetails.map {
        ImageDetails(
            id = it.id,
            imageId = it.imageId ,
            imgSrcUrl = it.imgSrcUrl,
            nsfw = it.nsfw
        )
    }
}

fun NetworkImageDetailsContainer.asDatabaseModel(): Array<DatabaseImageDetails> {
    return imageDetails.map {
        DatabaseImageDetails(
            id = it.id,
            imageId = it.imageId ,
            imgSrcUrl = it.imgSrcUrl,
            nsfw = it.nsfw)
    }.toTypedArray()
}
