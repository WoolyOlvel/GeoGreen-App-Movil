package com.example.dinospizza

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class CustomAdapter(private var titles: List<String>, private var details: List<String>, private var cost: List<String>, private var imagess: Array<Int>)  : RecyclerView.Adapter<CustomAdapter.ViewHolder>()
{


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var itemImage : ImageView = itemView.findViewById(R.id.custom1_img)
        var itemtitle : TextView = itemView.findViewById(R.id.custom1_title)
        var itemDetail : TextView = itemView.findViewById(R.id.custom1_sub)
        var itemcosto : TextView = itemView.findViewById(R.id.custom1_pre)

        init {
            itemView.setOnClickListener {
                var position : Int = adapterPosition
                itemView.context.startActivity(Intent(itemView.context, MainCompra::class.java).apply {
                    putExtra("valor", "Planta1")
                    putExtra("pos", position.toString())
                })


            }

        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.recommended_recycler_items, viewGroup,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return titles.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.itemtitle.text = titles[i]
        viewHolder.itemDetail.text = details[i]
        viewHolder.itemcosto.text = "$" + cost[i]
        //Picasso.get().load(image[i]).into(viewHolder.itemImage)
        viewHolder.itemImage.setImageResource(imagess[i])
    }
}