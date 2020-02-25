package com.imgur.imgurapidemo.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders

import com.imgur.imgurapidemo.R
import com.imgur.imgurapidemo.databinding.FragmentImageDetailBinding


class ImageDetailFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                               savedInstanceState: Bundle?): View? {

        val application = requireNotNull(activity).application

        val binding = FragmentImageDetailBinding.inflate(inflater)
        binding.setLifecycleOwner(this)
        val imageDetails = ImageDetailFragmentArgs.fromBundle(arguments!!).selectedImage
        val viewModelFactory = DetailViewModelFactory(imageDetails, application)
        binding.viewModel = ViewModelProviders.of(
            this, viewModelFactory).get(DetailViewModel::class.java)
        return binding.root

    }


}
