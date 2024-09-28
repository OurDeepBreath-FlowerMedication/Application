package com.example.flowermedication.custom_view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class TimeBarView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val backgroundPaint = Paint().apply {
        color = Color.LTGRAY
    }

    private val highlightPaint = Paint().apply {
        color = Color.GREEN
    }

    private var startTime = 0
    private var endTime = 0
    val timeLength = 30.0
    val marge_width = 0.03

    private val textPaint = Paint().apply {
        color = Color.BLACK
        textSize = 25f
        textAlign = Paint.Align.CENTER
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val width = width
        val height = height

        // 배경 그리기
        canvas.drawRect(
            (width*marge_width).toFloat(),
            (height / 2 - 10).toFloat(),
            (width*(1-marge_width)).toFloat(),
            (height / 2 + 10).toFloat(), backgroundPaint)

        // 시간에 따른 하이라이트 위치 계산
        val startX = width * (1-marge_width) * (startTime / timeLength)+(width*marge_width)
        val endX = width * (1-marge_width) * (endTime / timeLength)+(width*marge_width)

        // 하이라이트 구간 그리기
        canvas.drawRect(
            startX.toFloat(),
            (height / 2 - 10).toFloat(),
            endX.toFloat(),
            (height / 2 + 10).toFloat(), highlightPaint)

        // 시간 간격마다 숫자 표시
        drawTimeLabels(canvas, width, (width*marge_width).toFloat() , height)
    }

    private fun drawTimeLabels(canvas: Canvas, width: Int, start_width:Float, height: Int) {
        val hours = 7..22 // 예시로 7시부터 22시까지 표시
        val interval = width / (timeLength/2) // 한 시간당 간격


        for (hour in hours) {
            val position = (hour - 7) * interval
            if(((hour - 7)==(startTime/2.0).toInt()) || ((hour - 7)==(endTime/2.0).toInt())) {
                if(hour<12){
                    canvas.drawText("${hour}AM", position.toFloat()+start_width, (height / 2 + 50).toFloat(), textPaint)
                }else if(hour==12){
                    canvas.drawText("${hour}PM", position.toFloat()+start_width, (height / 2 + 50).toFloat(), textPaint)
                }else{
                    canvas.drawText("${hour%12}PM", position.toFloat()+start_width, (height / 2 + 50).toFloat(), textPaint)
                }

            }
        }
    }

    fun setStartTime(hour: Int, minute : Boolean) {
        startTime = (hour- 7)*2 + if (minute) 0 else 1
    }

    fun setEndTime(hour: Int, minute : Boolean) {
        endTime = (hour- 7)*2 + if (minute) 0 else 1
    }
}
