package com.example.demoapp

import android.app.Activity
import android.view.LayoutInflater
import android.view.LayoutInflater.*
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso

class MyAdapter(val context :Activity, val productArrayList : List<MyDataItem>):
    RecyclerView.Adapter<MyAdapter.MyViewHolder>(){
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title : TextView
        var image : ShapeableImageView
        init {
            title=itemView.findViewById(R.id.ProductTitle)
            image=itemView.findViewById(R.id.ProductImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView= from(context).inflate(R.layout.eachitem, parent,false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return productArrayList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem= productArrayList[position]
        holder.title.text=currentItem.title
        Picasso.get().load(currentItem.image).into(holder.image);
    }
}
