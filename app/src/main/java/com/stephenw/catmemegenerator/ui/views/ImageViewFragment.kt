package com.stephenw.catmemegenerator.ui.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.stephenw.catmemegenerator.R
import com.stephenw.catmemegenerator.Utils.BASE_URL
import com.stephenw.catmemegenerator.Utils.Resource
import com.stephenw.catmemegenerator.Utils.loadImage
import com.stephenw.catmemegenerator.databinding.FragmentImageViewBinding
import com.stephenw.catmemegenerator.ui.MainViewModel

class ImageViewFragment : Fragment(R.layout.fragment_image_view) {

    private val viewModel: MainViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentImageViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentImageViewBinding.bind(view)
        binding.apply {
            viewModel.catImages.observe(viewLifecycleOwner, {
                when (it) {
                    is Resource.Success -> {
                        catImage.loadImage(BASE_URL + it.data.url?.substring(1))
                    }
                }
            })
        }
    }

}