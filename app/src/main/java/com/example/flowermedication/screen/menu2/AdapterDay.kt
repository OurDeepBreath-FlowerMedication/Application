package com.example.flowermedication.screen.menu2

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flowermedication.R

class AdapterDay(val view : View): RecyclerView.Adapter<DayView>() {
    var adapter_schedule : AdapterSchedule ?= null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayView {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.menu2_day_schedules, parent, false)
        return DayView(view)
    }

    override fun onBindViewHolder(holder: DayView, position: Int) {
        adapter_schedule = AdapterSchedule(position)
        holder.setDaySchedules(position, view, adapter_schedule as AdapterSchedule)
    }

    override fun getItemCount(): Int {
        return 7
    }

    fun updateData(){
        adapter_schedule?.updateData()
        notifyDataSetChanged()
    }


}

class DayView(itemView : View) : RecyclerView.ViewHolder(itemView){
    fun setDaySchedules(position:Int, view: View, adapter_schedule : AdapterSchedule){
        var schedules : RecyclerView = itemView.findViewById(R.id.schedules)
        schedules.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.VERTICAL, false)
        schedules.adapter = adapter_schedule
    }
}