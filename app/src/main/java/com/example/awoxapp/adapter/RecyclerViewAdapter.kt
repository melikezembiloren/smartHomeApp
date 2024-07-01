package com.example.awoxapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.awoxapp.R
import com.example.awoxapp.data.DevicesListData

class RecyclerViewAdapter(private val context: Context, private val dataItemList: List<DevicesListData>):
RecyclerView.Adapter<RecyclerViewAdapter.DataListViewHolder>() //böylece adapter özelliğini alan bir sınıf oldu.
{
    inner class DataListViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

        var listImage = itemView.findViewById<ImageView>(R.id.listImage)
        var listName = itemView.findViewById<TextView>(R.id.listName) //burada görsel nesnelere erişildi fakat henüz tasarıma erişilmedi

        /**fun bind(data: DevicesListData) {
            listImage.setImageResource(data.image)
            listName.text = data.name
        }**/
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataListViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item_main, parent, false)//(tasarimin yeri, yazilimsal tasarim, false = baska bir tasarimin buraya bağlanmayacagini belirtiyoruz)
        return DataListViewHolder(view) //tasaarımı buraya aktarıyoruz
    }


    override fun onBindViewHolder(holder: DataListViewHolder, position: Int) { //hangi datanın hangi görsel nesneye aktarılacağı belirlit

        val deviceImage = dataItemList[position]
        holder.listImage.setImageResource(deviceImage.image)

        val deviceName = dataItemList[position]
        holder.listName.text = deviceName.name


        /**val listData = dataItemList[position]
        listData?.let {
            holder.bind(it)
        }**/
    }

    override fun getItemCount(): Int {
        return dataItemList.size //listede kaç tane item olacağı / satır sayısı bildirilir
    }
}