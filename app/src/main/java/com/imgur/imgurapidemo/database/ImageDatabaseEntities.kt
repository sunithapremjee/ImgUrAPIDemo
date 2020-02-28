package com.imgur.imgurapidemo.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.imgur.imgurapidemo.domain.ImageDetails


@Entity
data class DatabaseImageDetails constructor(

    @PrimaryKey
    var id: String,
    var title: String? = null,
    var link: String? = null,
    var nsfw: Boolean? = null,
    var vote: Long? = null,
    var views: Int? = null,

    var description:  String? = null,

    var datetime: Int? = null,

    var type: String? = null,

    var animated: Boolean? = null,

    var width: Int? = null,

    var height: Int? = null,

    var size: Int? = null,

    var bandwidth: Long? = null,

    var favorite: Boolean? = null,

    var section: String? = null,

    var accountUrl:  String? = null,

    var accountId:  Int? = null,

    var isAd: Boolean? = null,

    var inMostViral: Boolean? = null,

    var hasSound: Boolean? = null,

  //  var tags:  List<String>? = null,

    var adType: Int? = null,

    var adUrl: String? = null,

    var edited: Int? = null,

    var inGallery: Boolean? = null,

   // var adConfig:  AdConfig? = null,

    var commentCount:  Int? = null,

    var favoriteCount:  Int? = null,

    var ups:  Integer? = null,

    var downs:  Integer? = null,

    var points:  Integer? = null,

    var score: Int? = null,

    var isAlbum: Boolean? = null
)

data class AdConfig (

    var safeFlags:  List<String>? = null,

    var highRiskFlags:  List<Any>? = null,

    var unsafeFlags:  List<Any>? = null,

    var wallUnsafeFlags:  List<Any>? = null,

    var showsAds: Boolean? = null
)

fun List<DatabaseImageDetails>.asDomainModel(): List<ImageDetails> {
    return map {
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
           // tags = it.tags,
            adType = it.adType,
            adUrl =it.adUrl,
            edited = it.edited,
            inGallery =it.inGallery,
          //  adConfig = it.adConfig,
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