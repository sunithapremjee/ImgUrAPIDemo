package com.imgur.imgurapidemo.network

import com.imgur.imgurapidemo.database.DatabaseImageDetails
import com.imgur.imgurapidemo.domain.ImageDetails
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.RawValue

@JsonClass(generateAdapter = true)
data class NetworkImageDetailsContainer(val imageDetails: List<NetworkImageDetails>)

/**
 * ImageDetails.
 */
@JsonClass(generateAdapter = true)
data class NetworkImageDetails(
    @Json(name = "id")
    val id: String,
    @Json(name = "link")
    val link: String?=null,

    @Json(name = "nsfw")
    val nsfw:Boolean?=null,

    @Json(name = "title")
    val title:String?=null,

    @Json(name = "views")
    val views:Int?=null,

    @Json(name = "vote")
    val vote:Long?=null,
    @Json(name = "favorite")
var favorite: Boolean? = null,
    @Json(name = "description")
    var description: Any? = null,
    @Json(name = "datetime")
    var datetime: Int? = null,
    @Json(name = "type")
    var type: String? = null,
    @Json(name = "animated")
    var animated: Boolean? = null,
    @Json(name = "width")
    var width: Int? = null,
    @Json(name = "height")
    var height: Int? = null,
    @Json(name = "size")
    var size: Int? = null,

    @Json(name = "bandwidth")
    var bandwidth: Int? = null,
    @Json(name = "section")
var section: String? = null,
    @Json(name = "account_url")
var accountUrl: Any? = null,
    @Json(name = "account_id")
var accountId: Any? = null,
    @Json(name = "is_ad")
var isAd: Boolean? = null,
    @Json(name = "in_most_viral")
var inMostViral: Boolean? = null,
    @Json(name = "has_sound")
var hasSound: Boolean? = null,
    @Json(name = "tags")
var tags:  List<Any>? = null,
    @Json(name = "ad_type")
var adType: Int? = null,
    @Json(name = "ad_url")
var adUrl: String? = null,
    @Json(name = "edited")
var edited: Int? = null,
    @Json(name = "in_gallery")
var inGallery: Boolean? = null,

    @Json(name = "ad_config")
var adConfig: AdConfig? = null,
    @Json(name = "comment_count")
var commentCount: Any? = null,
    @Json(name = "favorite_count")
var favoriteCount: Any? = null,
    @Json(name = "ups")
var ups:  Any? = null,
    @Json(name = "downs")
var downs:  Any? = null,
    @Json(name = "points")
var points: Any? = null,
    @Json(name = "score")
var score: Int? = null,
    @Json(name = "is_album")
var isAlbum: Boolean? = null
)

data class AdConfig (
    @Json(name = "safeFlags")
    var safeFlags: List<String>? = null,
    @Json(name = "highRiskFlags")
    var highRiskFlags:  List<Any>? = null,
    @Json(name = "unsafeFlags")
    var unsafeFlags: List<Any>? = null,
    @Json(name = "wallUnsafeFlags")
    var wallUnsafeFlags: List<Any>? = null,
    @Json(name = "showsAds")
    var showsAds: Boolean? = null
)

/**
 * Convert Network results to database objects
 */
fun NetworkImageDetailsContainer.asDomainModel(): List<ImageDetails> {
    return imageDetails.map {
        ImageDetails(

            id = it.id,
            link = it.link,
            nsfw = it.nsfw,
            title = it.title,
            views = it.views,
            vote = it.vote,
            description = it.description,
            datetime = it.datetime,
            type = it.type,
            animated = it.animated,
            width = it.width,
            height = it.height,
            size = it.size,
            bandwidth = it.bandwidth,
            favorite = it.favorite,
            section = it.section,
            accountUrl = it.accountUrl,
            accountId = it.accountId,
            isAd =it.isAd,
            inMostViral = it.inMostViral,
            hasSound = it.hasSound,
            tags = it.tags,
            adType = it.adType,
            adUrl =it.adUrl,
            edited = it.edited,
            inGallery =it.inGallery,
           // adConfig = it.adConfig,
            commentCount = it.commentCount,

            favoriteCount = it.favoriteCount,
            ups = it.ups,
            downs = it.downs,
            points =it.points,
            score = it.score,
            isAlbum = it.isAlbum

        )
    }
}

fun NetworkImageDetailsContainer.asDatabaseModel(): Array<DatabaseImageDetails> {
    return imageDetails.map {
        DatabaseImageDetails(
            id = it.id,
            link = it.link,
            nsfw = it.nsfw,
            title = it.title,
            views = it.views,
            vote = it.vote,
                    description = it.description,
            datetime = it.datetime,
            type = it.type,
            animated = it.animated,
            width = it.width,
            height = it.height,
            size = it.size,
            bandwidth = it.bandwidth,
            favorite = it.favorite,
            section = it.section,
            accountUrl = it.accountUrl,
            accountId = it.accountId,
            isAd =it.isAd,
            inMostViral = it.inMostViral,
            hasSound = it.hasSound,
            tags = it.tags,
            adType = it.adType,
            adUrl =it.adUrl,
            edited = it.edited,
            inGallery =it.inGallery,
            //adConfig = it.adConfig,
            commentCount = it.commentCount,

            favoriteCount = it.favoriteCount,
            ups = it.ups,
            downs = it.downs,
            points =it.points,
            score = it.score,
            isAlbum = it.isAlbum
        )
    }.toTypedArray()
}
