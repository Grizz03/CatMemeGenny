package com.stephenw.catmemegenerator.ui.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.stephenw.catmemegenerator.R
import com.stephenw.catmemegenerator.databinding.FragmentDashboardBinding
import com.stephenw.catmemegenerator.ui.MainViewModel

class DashboardFragment : Fragment(R.layout.fragment_dashboard) {
    private val viewModel: MainViewModel by activityViewModels()

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val filterItems = resources.getStringArray(R.array.filter_menu)
        val filterAdapter = ArrayAdapter(requireContext(), R.layout.dropdown, filterItems)

        val binding = FragmentDashboardBinding.bind(view)
        binding.apply {

            menuImageFilterInput.setAdapter(filterAdapter)
            menuImageFilterInput.setText("None", false)

            menuImageFilterInput.setOnItemClickListener { parent, view, position, id ->
                if (parent?.getItemAtPosition(position).toString() == "None") viewModel.filter =
                    null
                else viewModel.filter = parent?.getItemAtPosition(position).toString().lowercase()
            }

            radioGroup.setOnCheckedChangeListener { group, checkedId ->
                val radioButton: RadioButton = group.findViewById(checkedId)
                viewModel.imageType =
                    MainViewModel.ImageType.valueOf(radioButton.text.toString().uppercase())
            }

            btnCreate.setOnClickListener {
                viewModel.getImage()
                findNavController().navigate(R.id.action_dashboardFragment_to_imageViewFragment)
            }
        }
    }
}