package com.example.splitter.Fragment

import android.R
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.splitter.Adapter.MemberAdapter
import com.example.splitter.SwipeToDelete
import com.example.splitter.ViewModel.MemberViewModel
import com.example.splitter.databinding.FragmentParticulatTripBinding
import com.example.splitter.entities.Members
import java.lang.reflect.Member


class ParticulatTrip : Fragment() {
    private val args by navArgs<ParticulatTripArgs>()
    private var _binding: FragmentParticulatTripBinding? = null
    lateinit var viewModel: MemberViewModel
    private val binding get() = _binding!!
    lateinit var adapter: MemberAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentParticulatTripBinding.inflate(inflater, container, false)
        val view = binding.root
        adapter= MemberAdapter(args.currentUser)
        binding.MembersRecyclerView.adapter=adapter
        binding.MembersRecyclerView.layoutManager=LinearLayoutManager(requireContext())


        viewModel= ViewModelProvider(this).get(
            MemberViewModel(
                this.requireActivity().application
            )::class.java
        )
        viewModel.allMembers.observe(viewLifecycleOwner, { Members ->
            var temp:MutableList<Members> = mutableListOf()

            for(i in Members)
            {
                if(i.kid==args.currentUser.tid)
                    temp.add(i)

            }
            adapter.updateUI(temp)

        })

        binding.btnCalc.setOnClickListener {
            val action=ParticulatTripDirections.actionParticulatTripToCalculateFragment(args.currentUser)
            findNavController().navigate(action)
        }
        binding.fabAddMember.setOnClickListener {



            val action=ParticulatTripDirections.actionParticulatTripToAddNameFragment(args.currentUser)
            findNavController().navigate(action)
        }

        val obj= object : SwipeToDelete(requireContext(),0, ItemTouchHelper.LEFT)
        {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                viewModel.deleteMember(adapter.getMember(viewHolder.adapterPosition))
            }
        }
        val itemTouchHelper=ItemTouchHelper(obj)
        itemTouchHelper.attachToRecyclerView(binding.MembersRecyclerView)

        return view
    }



}