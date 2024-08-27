package com.example.flowermedication.screen.menu3

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flowermedication.R
import java.util.Date

class AdapterMeal: RecyclerView.Adapter<MealView>(){
    var adapterMedication : AdapterMedication ?= null
    val meals : List<String> = listOf("아침", "점심", "저녁")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealView {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.menu3_day, parent, false)
        return MealView(view)
    }

    override fun onBindViewHolder(holder: MealView, position: Int) {
        adapterMedication = AdapterMedication(position)
        holder.setMeal(position, meals[position], adapterMedication as AdapterMedication)
    }

    override fun getItemCount(): Int {
        return 3
    }

    fun updateData(){
        adapterMedication?.updateData()
        notifyDataSetChanged()
    }
}

class MealView(val itemView: View): RecyclerView.ViewHolder(itemView){
    fun setMeal(position : Int, meal:String, adapterMedication:AdapterMedication){
        val meal_time_text : TextView = itemView.findViewById(R.id.meal_time_text)
        meal_time_text.text = meal

        val medication_list : RecyclerView = itemView.findViewById(R.id.medication_list)
        medication_list.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.VERTICAL, false)
        medication_list.adapter = adapterMedication
    }
}