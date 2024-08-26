package com.example.awoxapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.awoxapp.R
import com.example.awoxapp.Repository.entity.Devices
import com.example.awoxapp.data.PlatformsData
import com.example.awoxapp.detailedDevicesActivity.controlTvActivity

class RecyclerViewAdapterTVRemote(private val context: Context, val dataItemList: ArrayList<PlatformsData>):
RecyclerView.Adapter<RecyclerViewAdapterTVRemote.DataListViewHolder>() //böylece adapter özelliğini alan bir sınıf oldu.
{


    inner class DataListViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

        var listImage = itemView.findViewById<ImageView>(R.id.platformListImage)
  }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataListViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item_remote_tv, parent, false)//(tasarimin yeri, yazilimsal tasarim, false = baska bir tasarimin buraya bağlanmayacagini belirtiyoruz)
        return DataListViewHolder(view) //tasarımı buraya aktarıyoruz
    }


    override fun onBindViewHolder(holder: DataListViewHolder, position: Int) { //hangi datanın hangi görsel nesneye aktarılacağı belirlit

        val deviceImage = dataItemList[position]
        if (deviceImage != null) {
            deviceImage.let {
                Glide.with(holder.itemView.context)
                    .load(it.image)
                    .centerCrop()
                    .into(holder.listImage)
            }

        }
    }



    override fun getItemCount(): Int {
        return dataItemList.size //listede kaç tane item olacağı / satır sayısı bildirilir
    }

}