package com.example.dinospizza

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView



class CustomOferta(private var image: Array<Int>, private var titles: List<String>, private var details: List<String>, private var cost: List<String>, private var cali: List<String>, private var desc: List<String>, private var lim: List<String>,)  : RecyclerView.Adapter<CustomOferta.ViewHolder>()
{


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var itemImage : ImageView = itemView.findViewById(R.id.custom2_img)
        var itemtitle : TextView = itemView.findViewById(R.id.custom2_title)
        var itemDetail : TextView = itemView.findViewById(R.id.custom2_sub)
        var itemcosto : TextView = itemView.findViewById(R.id.custom2_pre)
        var itemcali: TextView = itemView.findViewById(R.id.custom2_cali)
        var itemdesc: TextView = itemView.findViewById(R.id.custom2_descue)
        var itemlim: TextView = itemView.findViewById(R.id.custom2_lim)


        init {
            itemView.setOnClickListener {
                var position : Int = adapterPosition
                itemView.context.startActivity(Intent(itemView.context, MainOferta::class.java).apply {
                    putExtra("valor","Paquete1")
                    putExtra("pos", position.toString())
                })



            }

        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.allmenu_recycler_items, viewGroup,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return titles.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.itemtitle.text = titles[i]
        viewHolder.itemDetail.text = details[i]
        viewHolder.itemcosto.text = "$" + cost[i]
        viewHolder.itemcali.text = cali[i]
        viewHolder.itemdesc.text = desc[i]
        viewHolder.itemlim.text = lim[i]
        //Picasso.get().load(image[i]).into(viewHolder.itemImage)
        viewHolder.itemImage.setImageResource(image[i])
    }
}