package com.example.splitter.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.splitter.R
import com.example.splitter.entities.Members


class MemberAdapter: RecyclerView.Adapter<MemberAdapter.MemberViewHolder>() {


    var arrList = ArrayList<Members>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberViewHolder {

        val x=   MemberViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_member,parent,false))

        return x


    }

    override fun onBindViewHolder(holder: MemberViewHolder, position: Int) {

        holder.txtMember.text=arrList[position].MemberName
        holder.tid.text=arrList[position].kid.toString()
        holder.rowItemMember.setOnClickListener {
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


}