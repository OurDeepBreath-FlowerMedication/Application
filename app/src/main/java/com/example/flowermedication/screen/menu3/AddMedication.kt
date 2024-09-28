package com.example.flowermedication.screen.menu3

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.flowermedication.R
import com.example.flowermedication.get_data.create_medication
import com.example.flowermedication.get_data.mealDay
import com.example.flowermedication.get_data.routin_meal
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class AddMedication : AppCompatActivity() {
    var ableDays : List<Boolean> = listOf(false, false, false, false, false, false, false)
    fun menageDays(meal : Int, day_cycles:List<View>){
        val day_text = listOf<TextView>(
            findViewById(R.id.day0),
            findViewById(R.id.day1),
            findViewById(R.id.day2),
            findViewById(R.id.day3),
            findViewById(R.id.day4),
            findViewById(R.id.day5),
            findViewById(R.id.day6)
        )

        lifecycleScope.launch {
            val mealDayDeffered = async { mealDay(meal) }
            val mealDay:List<Boolean>? = mealDayDeffered.await()
            if(mealDay!=null){
                ableDays = mealDay
                for (day in 0..6) {
                    if(mealDay[day]) {
                        day_text[day].setTextColor(Color.GRAY)
                        day_cycles[day].setBackgroundResource(R.drawable.circle_disable)
                    }else{
                        day_text[day].setTextColor(Color.BLACK)
                        day_cycles[day].setBackgroundResource(R.drawable.circle_default)
                    }
                }
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu3_add_medication)

        val custom_schedule_edit: EditText = findViewById(R.id.custom_schedule_edit)
        val mealTime: RadioGroup = findViewById(R.id.mealTime)
        val interval: RadioGroup = findViewById(R.id.interval)
        val useCheck: Switch = findViewById(R.id.useCheck)

        lifecycleScope.launch {
            val eat_meal: List<Boolean>? = routin_meal()

            val breakfast : RadioButton = findViewById(R.id.breakfast)
            val lunch : RadioButton = findViewById(R.id.lunch)
            val dinner : RadioButton = findViewById(R.id.dinner)

            if(eat_meal!=null){
                if(eat_meal[0]) breakfast.isEnabled = false
                if(eat_meal[1]) lunch.isEnabled = false
                if(eat_meal[2]) dinner.isEnabled = false
            }

            useCheck.isChecked = true
            useCheck.setOnClickListener {
                if (useCheck.isChecked) {
                    useCheck.text = "복약기 사용"
                } else {
                    useCheck.text = "직접 섭취"
                }
            }

            val day_cycles : List<View> = listOf<View>(
                findViewById(R.id.day_cycle0),
                findViewById(R.id.day_cycle1),
                findViewById(R.id.day_cycle2),
                findViewById(R.id.day_cycle3),
                findViewById(R.id.day_cycle4),
                findViewById(R.id.day_cycle5),
                findViewById(R.id.day_cycle6)
            )
            var select_days: MutableList<Boolean> = MutableList(7) { false }

            breakfast.setOnClickListener{
                menageDays(0, day_cycles)
            }

            lunch.setOnClickListener{
                menageDays(1, day_cycles)
            }

            dinner.setOnClickListener{
                menageDays(2, day_cycles)
            }

            for (day in 0..6) {
                day_cycles[day].setOnClickListener {
                    if(!ableDays[day]) {
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

            val btn_medication_create: Button = findViewById(R.id.btn_medication_create)
            btn_medication_create.setOnClickListener {
                val getMealID = when (mealTime.checkedRadioButtonId) {
                    R.id.breakfast -> 0
                    R.id.lunch -> 1
                    R.id.dinner -> 2
                    else -> -1
                }
                val getIntervalID = when (interval.checkedRadioButtonId) {
                    R.id.before -> 0
                    R.id.with -> 1
                    R.id.after -> 2
                    else -> -1
                }

                for(i:Int in 0..6){
                    if(ableDays[i]){
                        select_days[i] = false
                    }
                }
                //if (custom_schedule_edit.text.toString().isEmpty()) {
                //    Toast.makeText(this@AddMedication, "복용약의 명칭을 입력해주세요.", Toast.LENGTH_SHORT).show()
                //} else
                if (getMealID == -1) {
                    Toast.makeText(this@AddMedication, "복용 시간을 선택해 주세요.", Toast.LENGTH_SHORT).show()
                } else if (getIntervalID == -1) {
                    Toast.makeText(this@AddMedication, "복용 간격을 선택해 주세요.", Toast.LENGTH_SHORT).show()
                } else {
                    lifecycleScope.launch {
                        // 서버에 데이터 전송
                        create_medication(
                            select_days,
                            findViewById<RadioButton>(interval.checkedRadioButtonId).text.toString(),//custom_schedule_edit.text.toString(),
                            getMealID,
                            getIntervalID,
                            useCheck.isChecked
                        );
                    }
                    finish()
                }
            }
        }
    }
}