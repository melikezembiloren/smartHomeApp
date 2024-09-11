package com.example.awoxapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.awoxapp.R
import com.example.awoxapp.Repository.entity.Devices
import com.example.awoxapp.data.DeviceList
import com.example.awoxapp.detailedDevicesActivity.controlTvActivity
import com.google.android.material.switchmaterial.SwitchMaterial
import kotlinx.coroutines.flow.combine

class RecyclerViewAdapterMain(private val context: Context, val dataItemList: ArrayList<Devices>):
RecyclerView.Adapter<RecyclerViewAdapterMain.DataListViewHolder>() //böylece adapter özelliğini alan bir sınıf oldu.
{

    inner class DataListViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

        var listName = itemView.findViewById<TextView>(R.id.listName) //burada görsel nesnelere erişildi fakat henüz tasarıma erişilmedi
        var switchButton = itemView.findViewById<SwitchMaterial>(R.id.switchButton)
        var switchText = itemView.findViewById<TextView>(R.id.switchText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataListViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item_main_activity_devices, parent, false)//(tasarimin yeri, yazilimsal tasarim, false = baska bir tasarimin buraya bağlanmayacagini belirtiyoruz)
        return DataListViewHolder(view) //tasarımı buraya aktarıyoruz
    }


    override fun onBindViewHolder(holder: DataListViewHolder, position: Int) { //hangi datanın hangi görsel nesneye aktarılacağı belirlit


        val device = dataItemList[position]

        if (device != null) {

            holder.listName.text = device.nameOfSavedDevice
        }



        holder.switchButton.setOnCheckedChangeListener { _, isChecked ->

            if(isChecked) {

                holder.switchText.text = "AÇIK"

            }else{

                holder.switchText.text = "KAPALI"
            }
        }




        holder.itemView.setOnClickListener {


            val id = dataItemList[position].idOfSavedDevice
            val type = dataItemList[position].typeOfSavedDevice
            val imageId = dataItemList[position].imageIdOfSavedDevice
            val name = dataItemList[position].nameOfSavedDevice


            val device = Devices(id, type, imageId, name)


            try {

                val intent =
                    when (type) {
                        "Televizyon" -> Intent(context, controlTvActivity::class.java).apply {
                            putExtra("DEVICE", device)
                            putExtra("POSITION", position)
                        }

                        else -> null
                    }

                intent?.let {
                    context.startActivity(it)
                }


            } catch (e: Exception) {
                Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
            }

        }
    }


    override fun getItemCount(): Int {
        return dataItemList.size //listede kaç tane item olacağı / satır sayısı bildirilir
    }

}