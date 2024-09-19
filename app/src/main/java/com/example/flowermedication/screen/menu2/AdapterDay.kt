package com.example.flowermedication.screen.menu2

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flowermedication.DaySchedule
import com.example.flowermedication.R

class AdapterDay(val fragment: Menu2Fragment): RecyclerView.Adapter<DayView>() {
    var adapter_schedule : AdapterSchedule ?= null
    var schedules: MutableList<DaySchedule> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayView {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.menu2_day_schedules, parent, false)
        return DayView(view)
    }

    override fun onBindViewHolder(holder: DayView, position: Int) {
        adapter_schedule = AdapterSchedule(fragment, position)
        holder.setDaySchedules(adapter_schedule as AdapterSchedule)

        var result_schedules : MutableList<DaySchedule> = mutableListOf()

        for(schedule in schedules){
            if(schedule.day[position]==1){
                result_schedules.add(schedule)
            }
        }

        adapter_schedule!!.updateData(result_schedules)
    }

    override fun getItemCount(): Int {
        return 7
    }

    fun updateData(dataList: MutableList<DaySchedule>){
        schedules.clear()
        schedules.addAll(dataList)
        notifyDataSetChanged()
    }


}

class DayView(itemView : View) : RecyclerView.ViewHolder(itemView){
    fun setDaySchedules(adapter_schedule : AdapterSchedule){
        var schedules : RecyclerView = itemView.findViewById(R.id.schedules)
        schedules.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.VERTICAL, false)
        schedules.adapter = adapter_schedule
    }
}