package com.example.myapplication

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.RecyclerViewItemLayoutBinding

class BonusRecyclerViewAdapter : RecyclerView.Adapter<BonusRecyclerViewAdapter.ViewHolder>() {

    private var userList = listOf<User>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RecyclerViewItemLayoutBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {}

    override fun getItemCount(): Int {
        return userList.size
    }


    class ViewHolder(private val binding: RecyclerViewItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newList: List<User>){
        userList = newList
        notifyDataSetChanged()
    }
}