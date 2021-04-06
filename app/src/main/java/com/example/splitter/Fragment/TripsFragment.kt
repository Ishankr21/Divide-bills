package com.example.splitter.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.splitter.Adapter.TripAdapter
import com.example.splitter.R
import com.example.splitter.ViewModel.TripViewModel
import com.example.splitter.databinding.FragmentTripsBinding

class TripsFragment : Fragment() {

    private var _binding:FragmentTripsBinding? = null
    lateinit var viewModel: TripViewModel
    private val binding get() = _binding!!
    lateinit var adapter: TripAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTripsBinding.inflate(inflater, container, false)
        val view = binding.root
        adapter= TripAdapter()
        binding.TripsRecyclerView.adapter=adapter
        binding.TripsRecyclerView.layoutManager=LinearLayoutManager(requireContext())
        viewModel= ViewModelProvider(this).get(TripViewModel::class.java)
        viewModel.alltrips.observe(viewLifecycleOwner,{trips->
            adapter.updateUI(trips)

        })
        binding.fabAdd.setOnClickListener {
            findNavController().navigate(R.id.action_tripsFragment_to_addFragment)
        }
        return view

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}