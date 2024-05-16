package com.example.dinospizza

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView


class CustomPedidos(private var titles: List<String>, private var name: String,  private var cos: List<String>,private var opc: List<String> )  : RecyclerView.Adapter<CustomPedidos.ViewHolder>()
{


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var itemImage : ImageView = itemView.findViewById(R.id.custom2_img)
        var itemtitle : TextView = itemView.findViewById(R.id.custom2_title)
        var itemname : TextView = itemView.findViewById(R.id.custom2_sub)
        var itepago : TextView = itemView.findViewById(R.id.custom2_lim)


        init {
            itemView.setOnClickListener {
                var position : Int = adapterPosition
            }

        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.allmenu_recycler_items3, viewGroup,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return titles.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.itemtitle.text = titles[i]
        viewHolder.itemname.text = name
        viewHolder.itepago.text = "$" + cos[i]




    }

}