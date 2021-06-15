package com.sklinn.roomexercisejune.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.sklinn.roomexercisejune.R
import com.sklinn.roomexercisejune.data.User
import com.sklinn.roomexercisejune.fragment.list.ListFragmentDirections
import kotlinx.android.synthetic.main.custom_rows.view.*

class Adapter(
): RecyclerView.Adapter<Adapter.myViewHolder>() {

    private var userList = emptyList<User>()

    class myViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.custom_rows, parent,false)
        return myViewHolder(view)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.itemView.tv_age.text = currentItem.age.toString()
        holder.itemView.tv_FirstName.text = currentItem.firstName
        holder.itemView.tv_lastName.text = currentItem.lastName

        holder.itemView.currentItem.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment2(currentItem)
            holder.itemView.findNavController().navigate(action)
        }

    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun setNewData(users: List<User>){
        this.userList = users
        notifyDataSetChanged()
    }
}