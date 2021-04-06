package com.example.splitter.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.splitter.Adapter.MemberAdapter
import com.example.splitter.ViewModel.MemberViewModel
import com.example.splitter.databinding.FragmentParticulatTripBinding


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
        adapter= MemberAdapter()
        binding.MembersRecyclerView.adapter=adapter
        binding.MembersRecyclerView.layoutManager=LinearLayoutManager(requireContext())

        //yahan pe viewmodel mai id pass kar rahe h...pata nhi sahi tareeka h ki nhi
        viewModel= ViewModelProvider(this).get(
            MemberViewModel(
                this.requireActivity().application,
                args.currentUser.tid
            )::class.java
        )
        viewModel.allMembers.observe(viewLifecycleOwner, { Members ->
            adapter.updateUI(Members)

        })
        binding.fabAddMember.setOnClickListener {
            val action=ParticulatTripDirections.actionParticulatTripToAddNameFragment(args.currentUser)
            findNavController().navigate(action)
        }

        return view
    }



}