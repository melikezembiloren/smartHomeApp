package com.example.awoxapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.awoxapp.R
import com.example.awoxapp.data.DevicesListData
import com.example.awoxapp.data.PlatformsData

class ListAdapterRemoteControlTV(context: Context,  dataArrayList: ArrayList<PlatformsData?>?):
    ArrayAdapter<PlatformsData?>
        (context, 0 , dataArrayList!!){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var view = convertView
        val listData = getItem(position)

        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.list_item_remote_tv, parent, false)


        }


        val listImage = view!!.findViewById<ImageView>(R.id.platformListImage)

        listData?.let {
            Glide.with(context)
                .load(it.image)
                .centerCrop()
                .into(listImage)
        }

        return view

    }
}
