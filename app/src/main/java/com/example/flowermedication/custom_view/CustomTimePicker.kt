package com.example.flowermedication.custom_view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.NumberPicker
import com.example.flowermedication.R

class CustomTimePicker @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val amPmPicker: NumberPicker
    private val hourPicker: NumberPicker
    private val minutePicker: NumberPicker

    var isAm: Boolean
        get() = amPmPicker.value == 0
        set(value) {
            amPmPicker.value = if (value) 0 else 1
        }

    init {
        // 레이아웃 초기화
        LayoutInflater.from(context).inflate(R.layout.custom_time_picker, this, true)

        // NumberPicker 초기화
        amPmPicker = findViewById(R.id.amPmPicker)
        hourPicker = findViewById(R.id.hourPicker)
        minutePicker = findViewById(R.id.minutePicker)

        // AM/PM 선택 설정
        amPmPicker.minValue = 0
        amPmPicker.maxValue = 1
        amPmPicker.displayedValues = arrayOf("AM", "PM")

        // 시간 선택 설정 (1~12시간)
        hourPicker.minValue = 1
        hourPicker.maxValue = 12
        hourPicker.wrapSelectorWheel = true
        hourPicker.value = 7

        // 분 선택 설정 (0과 30분만 표시)
        minutePicker.minValue = 0
        minutePicker.maxValue = 1
        minutePicker.displayedValues = arrayOf("00", "30")
        minutePicker.wrapSelectorWheel = true

        // AM/PM 변환 처리
        hourPicker.setOnValueChangedListener { picker, oldVal, newVal ->

            if(amPmPicker.value == 0 && oldVal == 7 && newVal == 6){
                picker.value = oldVal
            }else if(amPmPicker.value == 1 && oldVal == 10 && newVal == 11){
                picker.value = oldVal
            }

            // am 11 -> pm 12로 넘어갈 때
            if (amPmPicker.value == 0 && oldVal == 11 && newVal == 12) {
                toggleAmPm()
            }
            // pm 12 -> am 11로 넘어갈 때
            else if (amPmPicker.value == 1 && oldVal == 12 && newVal == 11) {
                toggleAmPm()
            }
        }
    }

    // AM/PM 전환 함수
    private fun toggleAmPm() {
        amPmPicker.value = 1 - amPmPicker.value
    }

    fun getAmPm():Boolean{
        return (amPmPicker.value == 1)
    }

    fun getHour():Int{
        return hourPicker.value
    }

    fun getMinute():Boolean{
        return (minutePicker.value == 1)
    }
}

