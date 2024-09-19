package com.example.flowermedication.screen.menu2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.flowermedication.DaySchedule
import com.example.flowermedication.R
import com.example.flowermedication.custom_view.TimeBarView
import com.example.flowermedication.get_data.delRoutin

class AdapterSchedule(val fragment: Menu2Fragment, val day:Int) : RecyclerView.Adapter<ScheduleView>(){
    var schedules: MutableList<DaySchedule> = mutableListOf()

    fun updateData(newList: MutableList<DaySchedule>){
        schedules.clear()  // 기존 데이터를 지우고
        schedules.addAll(newList)  // 새로운 데이터를 추가
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleView {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.menu2_schedule, parent, false)
        return ScheduleView(view)
    }

    override fun onBindViewHolder(holder: ScheduleView, position: Int) {
        holder.setSchedule(schedules, position, fragment, day)
    }

    override fun getItemCount(): Int {
        if(schedules!=null) {
            return schedules.size
        }
        return 0
    }
}

class ScheduleView(itemView : View) : RecyclerView.ViewHolder(itemView){
    fun setSchedule(schedules : List<DaySchedule>?, position: Int, fragment: Menu2Fragment, day:Int){
        val schedule_name : TextView = itemView.findViewById(R.id.schedule_name)
        val time_bar : TimeBarView = itemView.findViewById(R.id.time_bar)
        val delete_btn : ImageButton = itemView.findViewById(R.id.delete_btn)

        schedule_name.text = schedules?.get(position)?.schedule_name

        if(schedules != null) {
            time_bar.setStartTime(schedules[position].start_hour, schedules[position].start_minute)
            time_bar.setEndTime(schedules[position].end_hour, schedules[position].end_minute)
        }
        time_bar.invalidate()

        delete_btn.setOnClickListener{
            // 서버에 정보 보내서 삭제, 서버에서 데이터 제 전송 받는 등의 코드를 수행해서 다시 불러오기.
            if(schedules?.get(position)!=null){
                delRoutin(schedules.get(position)!!.id)
                Toast.makeText(itemView.context, "\"${schedules.get(position).schedule_name}\" 일정이 삭제되었습니다.", Toast.LENGTH_SHORT).show()
                fragment.refreashFragment(day)
            }
        }
    }
}