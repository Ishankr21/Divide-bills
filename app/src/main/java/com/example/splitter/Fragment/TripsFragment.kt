package com.example.splitter.Fragment

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.splitter.Adapter.TripAdapter
import com.example.splitter.R
import com.example.splitter.SwipeToDelete
import com.example.splitter.ViewModel.TripViewModel
import com.example.splitter.databinding.FragmentTripsBinding

class TripsFragment : Fragment(), SearchView.OnQueryTextListener {

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
        setHasOptionsMenu(true);
        binding.TripsRecyclerView.adapter=adapter
        binding.TripsRecyclerView.layoutManager=LinearLayoutManager(requireContext())
        viewModel= ViewModelProvider(this).get(TripViewModel::class.java)
        viewModel.alltrips.observe(viewLifecycleOwner,{trips->
            adapter.updateUI(trips)

        })
        binding.fabAdd.setOnClickListener {
            findNavController().navigate(R.id.action_tripsFragment_to_addFragment)
        }
        val obj= object : SwipeToDelete(requireContext(),0, ItemTouchHelper.LEFT)
        {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                viewModel.deleteTrip(adapter.getTrip(viewHolder.adapterPosition))
            }
        }
        val itemTouchHelper=ItemTouchHelper(obj)
        itemTouchHelper.attachToRecyclerView(binding.TripsRecyclerView)
        return view

    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
         inflater.inflate(R.menu.main_menu,menu)
        val search = menu?.findItem(R.id.menu_search)
        val searchView = search?.actionView as? SearchView
        searchView?.isSubmitButtonEnabled = true
        searchView?.setOnQueryTextListener(this)


    }

    override fun onQueryTextSubmit(query: String?): Boolean {
       return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
        if(query != null){
            searchDatabase(query)
        }
        return true
    }
    private fun searchDatabase(query: String) {
        val searchQuery = "%$query%"

        viewModel.searchDatabase(searchQuery).observe(this, { list ->
            list.let {
                adapter.updateUI(it)
            }
        })
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}