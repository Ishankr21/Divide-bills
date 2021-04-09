package com.example.splitter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.splitter.Adapter.MemberAdapter
import com.example.splitter.Fragment.ParticulatTripArgs
import com.example.splitter.ViewModel.MemberViewModel
import com.example.splitter.databinding.FragmentAddExpenseBinding
import com.example.splitter.databinding.FragmentCalculateBinding
import com.example.splitter.databinding.FragmentParticulatTripBinding


class CalculateFragment : Fragment() {
    private val args by navArgs<CalculateFragmentArgs>()
    private var _binding: FragmentCalculateBinding? = null
    lateinit var viewModel: MemberViewModel
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCalculateBinding.inflate(inflater, container, false)
        val view=binding.root
        viewModel= ViewModelProvider(this).get(
            MemberViewModel(
                this.requireActivity().application
            )::class.java
        )
       var list=viewModel.getAllMembers(args.currentUser.tid)
        var ans=0.0
        var string:String=""
        for(i in list)
        {
            ans+=i.spent
        }
        var share=ans/(list.size)
        string+="Per Person Cost "+ share.toString() + "\n"
        for(i in list)
        {
            if(i.spent==share)
                string+=i.MemberName+" pays nothing "+"\n"
            else if(i.spent<share)
                string+=i.MemberName+" pays "+(share-i.spent).toString()+"\n"
            else
            string+=i.MemberName+" gets "+(i.spent-share).toString()+"\n"

        }
        binding.txtcalc.setText(string)
       return view
    }


}