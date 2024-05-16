package com.example.dinospizza

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ReciclerAdapter(private var titles: List<String>, private var details: List<String>, private var imagen: List<Int>) : RecyclerView.Adapter<ReciclerAdapter.ViewHolder>() {

    inner class ViewHolder(itemView : View ) : RecyclerView.ViewHolder(itemView){
        val itemTitle : TextView = itemView.findViewById(R.id.textmap)
        val itemsubtitle: TextView = itemView.findViewById(R.id.textmedmap)
        val itemima: ImageView= itemView.findViewById(R.id.magmap)

        init {
            itemView.setOnClickListener {
                val position : Int = adapterPosition
                when(position){
                    0->{
                        itemView.context.startActivity(Intent(itemView.context, MapsActivity::class.java).apply {
                            putExtra("valor", "0")
                        })
                    }
                    1->{
                        itemView.context.startActivity(Intent(itemView.context, MapsActivity::class.java).apply {
                            putExtra("valor", "1")
                        })
                    }
                    2->{
                        itemView.context.startActivity(Intent(itemView.context, MapsActivity::class.java).apply {
                            putExtra("valor", "2")
                        })
                    }
                    3->{
                        itemView.context.startActivity(Intent(itemView.context, MapsActivity::class.java).apply {
                            putExtra("valor", "3")
                        })
                    }
                    4->{
                        itemView.context.startActivity(Intent(itemView.context, MapsActivity::class.java).apply {
                            putExtra("valor", "5")
                        })
                    }

                }

                //Toast.makeText(itemView.context, "click item # ${position + 1}", Toast.LENGTH_SHORT).show()
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return  ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemTitle.text = titles[position]
        holder.itemsubtitle.text = details[position]
        holder.itemima.setImageResource(imagen[position])

    }

    override fun getItemCount(): Int {
        return titles.size
    }

}