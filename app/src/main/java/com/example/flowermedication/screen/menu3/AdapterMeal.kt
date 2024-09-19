package com.example.flowermedication.screen.menu3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flowermedication.Medication
import com.example.flowermedication.R

class AdapterMeal(val day:Int, val fragment: Menu3Fragment): RecyclerView.Adapter<MealView>(){
    var adapterMedication : AdapterMedication ?= null
    val meals : List<String> = listOf("아침", "점심", "저녁")
    var medications: MutableList<Medication> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealView {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.menu3_meal, parent, false)
        return MealView(view)
    }

    override fun onBindViewHolder(holder: MealView, position: Int) {
        adapterMedication = AdapterMedication(position, day, fragment)
        holder.setMeal(position, meals[position], adapterMedication as AdapterMedication)

        var result_medications : MutableList<Medication> = mutableListOf()

        for(medication in medications){
            if(medication.meal_time==position){
                result_medications.add(medication)
            }
        }

        adapterMedication!!.updateData(result_medications)
    }

    override fun getItemCount(): Int {
        return 3
    }

    fun updateData(medication_list : MutableList<Medication>){
        medications.clear()
        medications.addAll(medication_list)
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