package com.example.splitter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.splitter.Adapter.MemberAdapter
import com.example.splitter.Fragment.AddNameFragmentArgs
import com.example.splitter.ViewModel.MemberViewModel
import com.example.splitter.databinding.FragmentAddExpenseBinding
import com.example.splitter.databinding.FragmentParticulatTripBinding
import com.example.splitter.entities.Members


class AddExpense : Fragment() {
    private var _binding: FragmentAddExpenseBinding? = null
    lateinit var viewModel: MemberViewModel
    private val args by navArgs<AddExpenseArgs>()
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        _binding = FragmentAddExpenseBinding.inflate(inflater, container, false)
        viewModel= ViewModelProvider(this).get(
            MemberViewModel(
                this.requireActivity().application
            )::class.java
        )

        binding.edPassedName.setText("Add Expense For "+args.currentMember.MemberName)
        binding.btnaddexpense.setOnClickListener {
            var amount:Int=Integer.parseInt(binding.edAmount.text.toString())
            var x=args.currentMember.spent+amount
            var updatedMember= Members(args.currentMember.id,args.currentMember.MemberName,args.currentMember.kid,x)
            viewModel.updateUser(updatedMember)
            Toast.makeText(requireContext(),"Expense Added ",Toast.LENGTH_LONG).show()
            val action=AddExpenseDirections.actionAddExpenseToParticulatTrip(args.currentUser)
            findNavController().navigate(action)
        }


        val view = binding.root

        return view
    }


}