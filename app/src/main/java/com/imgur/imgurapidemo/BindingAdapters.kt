package com.imgur.imgurapidemo

import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.imgur.imgurapidemo.database.DatabaseImageDetails
import com.imgur.imgurapidemo.domain.ImageDetails
import timber.log.Timber


@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: PagedList<DatabaseImageDetails>?) {
    Timber.d("bindRecyclerView")
    val adapter = recyclerView.adapter as ImageGridAdapter
    if( null != data)
        adapter.submitList(data)
}


/**
 * Uses the Glide library to load an image by URL into an [ImageView]
 */
@BindingAdapter("link")
fun bindImage(imgView: ImageView, imgeDetais:DatabaseImageDetails?) {
    Timber.d("bindImage")
    imgeDetais?.let {
        if(it.type.equals("video/mp4"))
        {

            val requestOptions = RequestOptions()
            requestOptions.isMemoryCacheable
            requestOptions.override(70, 70)
            Glide.with(imgView.context).setDefaultRequestOptions(requestOptions).load(imgeDetais.link)
                .into(imgView)

        }
        else {
            val imgUri = imgeDetais.link?.toUri()?.buildUpon()?.scheme("https")?.build()
            Glide.with(imgView.context)
                .load(imgUri)
                .into(imgView)
        }
    }

}