package com.example.wheel_of_fortune.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.wheel_of_fortune.R

//this adapter was made with the following tutorial: https://www.section.io/engineering-education/implementing-mvvm-architecture-in-android-using-kotlin/
class RecyclerViewAdapter(private val List: List<String>, val context: Context): RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): MyViewHolder {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_row,parent,false)
        return MyViewHolder(root)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(List[position])
    }

    override fun getItemCount(): Int {
        if(List.isEmpty()){
            Toast.makeText(context,"List is empty", Toast.LENGTH_LONG).show()
        }
        return List.size
    }

    class MyViewHolder(binding: View) : RecyclerView.ViewHolder(binding) {
        val textView: TextView = binding.findViewById(R.id.hiddenWordRow)
        fun bind(string: String){
            textView.text = string
        }
    }
}