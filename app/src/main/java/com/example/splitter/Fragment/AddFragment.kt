package com.example.splitter.Fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.splitter.R
import com.example.splitter.ViewModel.TripViewModel
import com.example.splitter.databinding.FragmentAddBinding
import com.example.splitter.databinding.FragmentTripsBinding
import com.example.splitter.entities.Trip


class AddFragment : Fragment() {
    private var _binding: FragmentAddBinding? = null
    lateinit var viewModel: TripViewModel
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModel= ViewModelProvider(this).get(TripViewModel::class.java)
        binding.btnAdd.setOnClickListener {
            inputUser()
        }
        return view
    }

    private fun inputUser() {
        val tripName=binding.EdTripName.text.toString()
        if(inputChecker(tripName)) {
            val trip = Trip(0, tripName)
            viewModel.insertTrip(trip)
            Toast.makeText(requireContext(),"Trip Entered",Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_addFragment_to_tripsFragment)

        }
        else
        {
            Toast.makeText(requireContext(),"Please Enter Trip Name",Toast.LENGTH_LONG).show()
        }
    }
    fun inputChecker(first:String):Boolean
    {
        return !(TextUtils.isEmpty(first))
    }


}