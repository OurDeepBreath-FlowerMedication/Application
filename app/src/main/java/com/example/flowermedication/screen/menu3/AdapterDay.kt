package com.example.flowermedication.screen.menu3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flowermedication.Medication
import com.example.flowermedication.R

class AdapterDay(val fragment: Menu3Fragment): RecyclerView.Adapter<DayView>() {
    var adapter_meal : AdapterMeal ?= null
    var medications: MutableList<Medication> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayView {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.menu3_day, parent, false)
        return DayView(view)
    }

    override fun onBindViewHolder(holder: DayView, position: Int) {
        adapter_meal = AdapterMeal(position, fragment)
        holder.setMealMedication(adapter_meal as AdapterMeal)

        var result_medications : MutableList<Medication> = mutableListOf()

        for(medication in medications){
            if(medication.day[position]==1){
                result_medications.add(medication)
            }
        }

        adapter_meal!!.updateData(result_medications)
    }

    override fun getItemCount(): Int {
        return 7
    }

    fun updateData(dataList: MutableList<Medication>){
        medications.clear()
        medications.addAll(dataList)
        notifyDataSetChanged()
    }


}

class DayView(itemView : View) : RecyclerView.ViewHolder(itemView){
    fun setMealMedication(adapter_meal : AdapterMeal){
        var meals : RecyclerView = itemView.findViewById(R.id.meal_list)
        meals.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.VERTICAL, false)
        meals.adapter = adapter_meal
    }
}