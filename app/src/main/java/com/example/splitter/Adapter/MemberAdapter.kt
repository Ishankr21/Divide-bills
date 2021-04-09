package com.example.splitter.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.splitter.Fragment.ParticulatTripDirections
import com.example.splitter.R
import com.example.splitter.entities.Members
import com.example.splitter.entities.Trip


class MemberAdapter(private var trip: Trip): RecyclerView.Adapter<MemberAdapter.MemberViewHolder>() {


    var arrList = ArrayList<Members>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberViewHolder {

            val x=   MemberViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_member,parent,false))

            return x


    }

    override fun onBindViewHolder(holder: MemberViewHolder, position: Int) {

        holder.txtMember.text=arrList[position].MemberName
        holder.tid.text=arrList[position].spent.toString()
        holder.rowItemMember.setOnClickListener {
            val action=ParticulatTripDirections.actionParticulatTripToAddExpense(arrList[position],trip)
            holder.itemView.findNavController().navigate(action)

        }

    }

    override fun getItemCount(): Int {

        return arrList.size
    }
    class MemberViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val txtMember: TextView =view.findViewById(R.id.txtMember)
        val tid:TextView=view.findViewById(R.id.txttid)
        val rowItemMember: LinearLayout =view.findViewById(R.id.layoutMember)



    }
    fun updateUI(newList: List<Members>)
    {
        arrList.clear()
        arrList.addAll(newList)
        notifyDataSetChanged()
    }
    fun getMember(pos:Int): Members
    {
        return arrList[pos];
    }


}
