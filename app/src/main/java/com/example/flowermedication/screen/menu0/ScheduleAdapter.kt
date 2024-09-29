package com.example.flowermedication.screen.menu0

import android.view.LayoutInflater
import android.view.View
import android.text.TextPaint
import android.util.Log
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.flowermedication.R
import com.example.flowermedication.TodaySchedule

class ScheduleAdapter : RecyclerView.Adapter<Holder>() {
    var todaySchedules = mutableListOf<TodaySchedule>()

    fun updateData(newList: MutableList<TodaySchedule>){
        todaySchedules.clear()  // 기존 데이터를 지우고
        todaySchedules.addAll(newList)  // 새로운 데이터를 추가
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.schedule_list_form, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val schedule = todaySchedules.get(position)
        holder.setSchedule(schedule)
    }

    override fun getItemCount(): Int {
        return todaySchedules.size
    }
}

class Holder(itemView: View) : RecyclerView.ViewHolder(itemView){
    fun setSchedule(todaySchedule: TodaySchedule){
        val content = itemView.findViewById<TextView>(R.id.content_text)
        val time = itemView.findViewById<TextView>(R.id.time_text)

        content.text = todaySchedule.schedule_name
        if(todaySchedule.isDone) {
            time.text = todaySchedule.doneTime
            content.paintFlags = content.paintFlags or TextPaint.STRIKE_THRU_TEXT_FLAG
        }else{
            time.text = ""
            content.paintFlags = content.paintFlags and TextPaint.STRIKE_THRU_TEXT_FLAG.inv()
        }
    }
}