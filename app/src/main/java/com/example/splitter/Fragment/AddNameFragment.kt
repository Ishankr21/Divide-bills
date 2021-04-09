package com.example.splitter.Fragment

import android.os.Bundle
import android.text.TextUtils

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.splitter.Adapter.MemberAdapter
import com.example.splitter.R
import com.example.splitter.ViewModel.MemberViewModel
import com.example.splitter.databinding.FragmentAddNameBinding

import com.example.splitter.entities.Members



class AddNameFragment : Fragment() {
    private val args by navArgs<AddNameFragmentArgs>()
    private var _binding: FragmentAddNameBinding? = null
    lateinit var viewModel: MemberViewModel
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddNameBinding.inflate(inflater, container, false)
        val view = binding.root

        viewModel= ViewModelProvider(this).get(MemberViewModel::class.java)
        binding.btnAddMember.setOnClickListener {
            inputMember()
        }
       return view
    }

    private fun inputMember() {
        val name=binding.EdMemberName.text.toString()
        if(inputChecker(name))
        {
            var temp:Int=args.currentUser.tid
            var newMenber=Members(0,name,temp,0.0)
            viewModel.insertMember(newMenber)
            Toast.makeText(requireContext(),"Member Entered ",Toast.LENGTH_LONG).show()
            val action= AddNameFragmentDirections.actionAddNameFragmentToParticulatTrip(args.currentUser)
            findNavController().navigate(action)
        }
        else
        {
            Toast.makeText(requireContext(),"Please Enter Member Name", Toast.LENGTH_LONG).show()
        }



    }
    fun inputChecker(first:String):Boolean
    {
        return !(TextUtils.isEmpty(first))
    }


}