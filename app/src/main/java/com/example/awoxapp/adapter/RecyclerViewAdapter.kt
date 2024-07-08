package com.example.awoxapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.awoxapp.R
import com.example.awoxapp.Repository.entity.Devices

class RecyclerViewAdapter(private val context: Context, val dataItemList: ArrayList<Devices>):
RecyclerView.Adapter<RecyclerViewAdapter.DataListViewHolder>() //böylece adapter özelliğini alan bir sınıf oldu.
{

    var onVerticalDotsClickListener : ((Int, View) -> Unit)? = null

    /*
    private var onVerticalDotsClickListener:  OnPopUpClickListener? = null

    fun setOnVerticalDotsClickListener(onPopUpClickListener:  OnPopUpClickListener?)
    {
        this.onVerticalDotsClickListener = onPopUpClickListener
    }

    interface OnPopUpClickListener{
        fun onVerticalDotsClicked(position: Int, item: View)
    }


     */
    inner class DataListViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

        var listImage = itemView.findViewById<ImageView>(R.id.listImage)
        var listName = itemView.findViewById<TextView>(R.id.listName) //burada görsel nesnelere erişildi fakat henüz tasarıma erişilmedi
        var verticalDots = itemView.findViewById<ImageButton>(R.id.image_Button_three_dots_list_item_of_main)

        /**fun bind(data: DevicesListData) {
            listImage.setImageResource(data.image)
            listName.text = data.name
        }**/
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataListViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item_main, parent, false)//(tasarimin yeri, yazilimsal tasarim, false = baska bir tasarimin buraya bağlanmayacagini belirtiyoruz)
        return DataListViewHolder(view) //tasarımı buraya aktarıyoruz
    }


    override fun onBindViewHolder(holder: DataListViewHolder, position: Int) { //hangi datanın hangi görsel nesneye aktarılacağı belirlit

        val deviceImage = dataItemList[position]
        if (deviceImage != null) {
            holder.listImage.setImageResource(deviceImage.imageIdOfSavedDevice)
        }

        val deviceName = dataItemList[position]
        if (deviceName != null) {
            holder.listName.text = deviceName.nameOfSavedDevice
        }



        holder.verticalDots.setOnClickListener {
            onVerticalDotsClickListener!!(position,holder.verticalDots)
        }

    }

        /**val listData = dataItemList[position]
        listData?.let {
            holder.bind(it)
        }**/


    override fun getItemCount(): Int {
        return dataItemList.size //listede kaç tane item olacağı / satır sayısı bildirilir
    }
}