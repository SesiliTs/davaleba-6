package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter

class Adapter(context: Context, myList: List<User>) :
    ArrayAdapter<User>(context, R.layout.recycler_view_item_layout, myList) {

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = LayoutInflater.from(context)
        return inflater.inflate(R.layout.recycler_view_item_layout, parent, false)
    }
}