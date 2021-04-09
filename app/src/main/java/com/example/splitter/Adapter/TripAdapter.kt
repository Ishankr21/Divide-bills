package com.example.splitter.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.splitter.Fragment.TripsFragment
import com.example.splitter.Fragment.TripsFragmentDirections
import com.example.splitter.R
import com.example.splitter.entities.Trip

class TripAdapter:RecyclerView.Adapter<TripAdapter.TripsViewHolder>() {


    var arrList = ArrayList<Trip>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TripsViewHolder {

        val x=   TripsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_trip,parent,false))

        return x


    }

    override fun onBindViewHolder(holder: TripsViewHolder, position: Int) {

            holder.txtTrip.text=arrList[position].TripName
            holder.rowItem.setOnClickListener {
                val action=TripsFragmentDirections.actionTripsFragmentToParticulatTrip(arrList[position])
                holder.itemView.findNavController().navigate(action)
            }
    }

    override fun getItemCount(): Int {

            return arrList.size
    }
    class TripsViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val txtTrip: TextView =view.findViewById(R.id.txtTrip)
        val rowItem:LinearLayout=view.findViewById(R.id.layoutTrip)


    }
    fun updateUI(newList: List<Trip>)
    {
        arrList.clear()
        arrList.addAll(newList)
        notifyDataSetChanged()
    }
    fun getTrip(pos:Int):Trip
    {
        return arrList[pos];
    }


}