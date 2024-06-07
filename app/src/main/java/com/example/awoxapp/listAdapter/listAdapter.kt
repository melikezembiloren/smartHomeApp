package com.example.awoxapp.listAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.awoxapp.R
import com.example.awoxapp.data.DevicesListData

class ListAdapter(context: Context, dataArrayList: ArrayList<DevicesListData?>?):
    ArrayAdapter<DevicesListData?>(context,
        0 , dataArrayList!!){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var view = convertView
        val listData = getItem(position)

        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)

        }

        val listImage = view!!.findViewById<ImageView>(R.id.listImage)
        val listName = view.findViewById<TextView>(R.id.listName)

        listData?.let {
            listImage.setImageResource(it.image)
            listName.text = it.name
        }

        return view

    }
}
