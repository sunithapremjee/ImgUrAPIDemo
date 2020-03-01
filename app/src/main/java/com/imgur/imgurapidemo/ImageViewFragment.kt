// Author:           Sunitha Premjee

package com.imgur.imgurapidemo

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.imgur.imgurapidemo.database.ImageDatabase
import com.imgur.imgurapidemo.databinding.ImageViewFragmentBinding
import timber.log.Timber


class ImageViewFragment : Fragment() {

    private val viewModel: ImageViewViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        ViewModelProviders.of(this, context?.let { ImageDatabase.getInstance(it) }?.let {
            ImageViewModelFactory(
                it,
                activity.application)
        })
            .get(ImageViewViewModel ::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        Timber.d("ImageViewFragment:onCreateView")

        val binding:ImageViewFragmentBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.image_view_fragment,
            container,
            false)


        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.setLifecycleOwner(this)

        // Giving the binding access to the ImageViewViewModel
        binding.viewModel = viewModel

        // Sets the adapter of the imageGrid RecyclerView with clickHandler lambda that
        // tells the viewModel when our property is clicked
        binding.imagesGrid.adapter = ImageGridAdapter(ImageGridAdapter.OnClickListener {
            viewModel.displayImageDetails(it)
        })

        // Observe the navigateToSelectedProperty LiveData and Navigate when it isn't null
        // After navigating, call displayPropertyDetailsComplete() so that the ViewModel is ready
        // for another navigation event.
        viewModel.navigateToSelectedImage.observe(viewLifecycleOwner, Observer {
            if ( null != it ) {
                // Must find the NavController from the Fragment
                this.findNavController().navigate(ImageViewFragmentDirections.actionShowDetail(it))
                // Tell the ViewModel we've made the navigate call to prevent multiple navigation
                viewModel.displayImageDetailsComplete()
            }
        })

        setHasOptionsMenu(true)
        return binding.root
    }



}
