package com.imgur.imgurapidemo.domain

import android.os.Parcelable
import com.imgur.imgurapidemo.database.AdConfig
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue


@Parcelize
data class ImageDetails(

    @Json(name = "id")
    var id: String? = null,
    @Json(name = "title")
    var title: String? = null,

    @Json(name = "description")
    var description: String? = null,
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
    @Json(name = "views")
    var views: Int? = null,
    @Json(name = "bandwidth")
    var bandwidth: Long? = null,
    @Json(name = "vote")
    var vote: Long? = null,
    @Json(name = "favorite")
    var favorite: Boolean? = null,
    @Json(name = "nsfw")
    var nsfw: Boolean? = null,
    @Json(name = "section")
    var section: String? = null,
    @Json(name = "account_url")
    var accountUrl: String? = null,
    @Json(name = "account_id")
    var accountId: Int? = null,
    @Json(name = "is_ad")
    var isAd: Boolean? = null,
    @Json(name = "in_most_viral")
    var inMostViral: Boolean? = null,
    @Json(name = "has_sound")
    var hasSound: Boolean? = null,
    //@Json(name = "tags")
   // var tags: List<String>? = null,
    @Json(name = "ad_type")
    var adType: Int? = null,
    @Json(name = "ad_url")
    var adUrl: String? = null,
    @Json(name = "edited")
    var edited: Int? = null,
    @Json(name = "in_gallery")
    var inGallery: Boolean? = null,
    @Json(name = "link")
    var link: String? = null,
    //@Json(name = "ad_config")
  //  var adConfig: @RawValue AdConfig? = null,
    @Json(name = "comment_count")
    var commentCount:  Int? = null,
    @Json(name = "favorite_count")
    var favoriteCount: Int? = null,
    @Json(name = "ups")
    var ups: Integer? = null,
    @Json(name = "downs")
    var downs: Integer? = null,
    @Json(name = "points")
    var points:Integer? = null,
    @Json(name = "score")
    var score: Int? = null,
    @Json(name = "is_album")
    var isAlbum: Boolean? = null
): Parcelable {

}
@Parcelize
data class AdConfig (
    @Json(name = "safeFlags")
    var safeFlags: @RawValue List<String>? = null,
    @Json(name = "highRiskFlags")
    var highRiskFlags: @RawValue List<Any>? = null,
    @Json(name = "unsafeFlags")
    var unsafeFlags: @RawValue List<Any>? = null,
    @Json(name = "wallUnsafeFlags")
    var wallUnsafeFlags:@RawValue  List<Any>? = null,
    @Json(name = "showsAds")
    var showsAds: Boolean? = null
):Parcelable
{

}
