package com.example.flowermedication.screen.menu2

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.flowermedication.R
import com.example.flowermedication.custom_view.CustomTimePicker
import com.example.flowermedication.custom_view.UnderLine
import com.example.flowermedication.get_data.*
import kotlinx.coroutines.*

class AddSchedule : AppCompatActivity() {

    val schedule_list : List<String> = listOf("--일정 선택--", "직접 입력", "아침", "점심", "저녁")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu2_add_schedule)

        lifecycleScope.launch {
            val selectied_schedule: List<Boolean>? = routin_meal()

            var schedule_spinner: Spinner = findViewById(R.id.schedule_spinner)
            val day_cycles = listOf<View>(
                findViewById(R.id.day_cycle0),
                findViewById(R.id.day_cycle1),
                findViewById(R.id.day_cycle2),
                findViewById(R.id.day_cycle3),
                findViewById(R.id.day_cycle4),
                findViewById(R.id.day_cycle5),
                findViewById(R.id.day_cycle6)
            )
            var select_days: MutableList<Boolean> = MutableList(7) { false }

            schedule_spinner.adapter = object :
                ArrayAdapter<String>(this@AddSchedule, android.R.layout.simple_list_item_1, schedule_list) {
                override fun isEnabled(position: Int): Boolean {
                    if (position == 0 || (position > 1 && selectied_schedule?.get(position - 2) == false)) {
                        return false
                    } else {
                        return true
                    }
                }

                override fun getDropDownView(
                    position: Int,
                    convertView: View?,
                    parent: ViewGroup
                ): View {
                    val view = super.getDropDownView(position, convertView, parent)
                    val textView = view as TextView
                    if (position == 0 || (position > 1 && selectied_schedule?.get(position - 2) == false)) {
                        textView.setTextColor(Color.GRAY)
                    } else {
                        textView.setTextColor(Color.BLACK)
                    }
                    return view
                }
            }

            schedule_spinner.onItemSelectedListener = object : OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val text: TextView = findViewById(R.id.custom_schedule_text)
                    val bar: UnderLine = findViewById(R.id.custom_schedule_bar)
                    val edit: EditText = findViewById(R.id.custom_schedule_edit)
                    if (position == 1) {
                        text.visibility = View.VISIBLE
                        bar.visibility = View.VISIBLE
                        edit.visibility = View.VISIBLE
                    } else {
                        text.visibility = View.GONE
                        bar.visibility = View.GONE
                        edit.visibility = View.GONE

                        if(position != 0){
                            for (day in 0..6) {
                                select_days[day] = true
                                day_cycles[day].setBackgroundResource(R.drawable.circle_select)
                            }
                        }
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }

            val start_time_set: CustomTimePicker = findViewById(R.id.start_time_set)
            val end_time_set: CustomTimePicker = findViewById(R.id.end_time_set)

            for (day in 0..6) {
                day_cycles[day].setOnClickListener {
                    if(schedule_spinner.getSelectedItemPosition()==0 || schedule_spinner.getSelectedItemPosition()==1){
                        if (select_days[day]) {
                            select_days[day] = false
                            day_cycles[day].setBackgroundResource(R.drawable.circle_default)
                        } else {
                            select_days[day] = true
                            day_cycles[day].setBackgroundResource(R.drawable.circle_select)
                        }
                    }

                }
            }

            val btn_schedule_create: Button = findViewById(R.id.btn_medication_create)
            btn_schedule_create.setOnClickListener {
                var startHour = start_time_set.getHour() + if (start_time_set.getHour()==12 || start_time_set.isAm) 0 else 12
                var endHour = end_time_set.getHour() + if (end_time_set.getHour()==12 || end_time_set.isAm) 0 else 12

                if(schedule_spinner.getSelectedItemPosition() == 0){
                    Toast.makeText(this@AddSchedule,
                        "일정을 선택해주세요."
                        , Toast.LENGTH_SHORT).show()
                }else if(select_days.all { it == false }){
                    Toast.makeText(this@AddSchedule,
                        "수행할 날짜를 하나 이상 선택해주세요."
                        , Toast.LENGTH_SHORT).show()
                }else if (startHour > endHour || (startHour == endHour && !(start_time_set.getMinute() == false && end_time_set.getMinute() == true))) {
                    Toast.makeText(this@AddSchedule,
                        "\"마감 시간\"을 \"시작 시간\" 보다 더 뒤에 설정해주세요."
                        , Toast.LENGTH_SHORT).show()
                } else {

                    val schedule_text: TextView = findViewById(R.id.custom_schedule_edit)
                    var select : Int = schedule_spinner.getSelectedItemPosition()-2
                    if(select==-1){
                        select = 3;
                    }

                    var routin_name : String = schedule_text.text.toString();
                    if(select == 0){
                        routin_name = "아침"
                    }else if(select == 1){
                        routin_name = "점심"
                    }else if(select == 2){
                        routin_name = "저녁"
                    }
                    create_routin(
                        select,
                        routin_name,
                        select_days,
                        startHour,
                        start_time_set.getMinute(),
                        endHour,
                        end_time_set.getMinute()
                    )
                    finish()
                }
            }
        }
    }
}