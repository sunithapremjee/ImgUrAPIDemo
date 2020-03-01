package com.imgur.imgurapidemo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.imgur.imgurapidemo.databinding.GridViewItemBinding
import com.imgur.imgurapidemo.domain.ImageDetails
import timber.log.Timber

class ImageGridAdapter( val onClickListener: OnClickListener ) :
    ListAdapter<ImageDetails, ImageGridAdapter.ImageDetailsViewHolder>(DiffCallback) {
    /**
     * The ImageDetailsViewHolder constructor takes the binding variable from the associated
     * GridViewItem, which nicely gives it access to the full [ImageDetails] information.
     */
    class ImageDetailsViewHolder(private var binding: GridViewItemBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(ImageDetails: ImageDetails) {

            Timber.d("ImageDetailsViewHolder:bind")

            binding.imagedetails = ImageDetails
            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }
    }

    /**
     * Allows the RecyclerView to determine which items have changed when the [List] of [ImageDetails]
     * has been updated.
     */
    companion object DiffCallback : DiffUtil.ItemCallback<ImageDetails>() {
        override fun areItemsTheSame(oldItem: ImageDetails, newItem: ImageDetails): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: ImageDetails, newItem: ImageDetails): Boolean {
            return oldItem.id == newItem.id
        }
    }

    /**
     * Create new [RecyclerView] item views (invoked by the layout manager)
     */

    /**
     * Replaces the contents of a view (invoked by the layout manager)
     */


    /**
     * Custom listener that handles clicks on [RecyclerView] items.  Passes the [ImageDetails]
     * associated with the current item to the [onClick] function.
     * @param clickListener lambda that will be called with the current [ImageDetails]
     */
    class OnClickListener(val clickListener: (ImageDetails: ImageDetails) -> Unit) {
        fun onClick(ImageDetails: ImageDetails) = clickListener(ImageDetails)
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ImageDetailsViewHolder {
        Timber.d("ImageGridAdapter:onCreateViewHolder")
        return ImageDetailsViewHolder(GridViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }


    override fun onBindViewHolder(holder: ImageDetailsViewHolder, position: Int) {
        val ImageDetails = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(ImageDetails)
        }
        holder.bind(ImageDetails)
    }
}