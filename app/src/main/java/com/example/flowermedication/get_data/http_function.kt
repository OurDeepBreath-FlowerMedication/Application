package com.example.flowermedication.get_data

import android.util.Log
import com.example.flowermedication.DaySchedule
import com.example.flowermedication.Medication

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object ServerIP{
    // 서버 설정
    val IP : String = "http://10.0.2.2:3000/"
}

// API 인터페이스 정의
interface ApiService {
    @GET("test")
    fun getData(): Call<List<String>>
}

fun serverConnect() {
    // Retrofit 인스턴스 생성
    val retrofit = Retrofit.Builder()
        .baseUrl(ServerIP.IP) // 에뮬레이터에서 로컬 서버로 연결
        .addConverterFactory(GsonConverterFactory.create()) // JSON -> 객체 변환
        .build()

    // API 서비스 생성
    val service = retrofit.create(ApiService::class.java)

    // 비동기로 요청 실행
    service.getData().enqueue(object : Callback<List<String>> {
        override fun onResponse(call: Call<List<String>>, response: Response<List<String>>) {
            if (response.isSuccessful) {
                val data = response.body()
                // 응답 출력
                data?.forEach {
                    Log.d("Server", it)
                }
            } else {
                Log.e("Server Error", "Response was not successful")
            }
        }

        override fun onFailure(call: Call<List<String>>, t: Throwable) {
            Log.e("Server Error", "Failed to connect to server: ${t.message}")
        }
    })
}

fun routin_check(
    select_days : MutableList<Boolean>,
    startHour : Int,
    startMinute : Boolean,
    endHour : Int,
    endMinute : Boolean) : Boolean{


    // Retrofit 인스턴스 => http통신을 위한 라이브러리
    val retrofit = Retrofit.Builder()
        .baseUrl(ServerIP.IP)
        .addConverterFactory(GsonConverterFactory.create()) // JSON -> 객체 변환
        .build()

    // API 서비스 생성
    val service = retrofit.create(ApiService::class.java)

    // 비동기로 요청 실행
    service.getData().enqueue(object : Callback<List<String>> {
        override fun onResponse(call: Call<List<String>>, response: Response<List<String>>) {
            if (response.isSuccessful) {
                val data = response.body()
                // 응답 출력
                data?.forEach {
                    Log.d("Server", it)
                }
            } else {
                Log.e("Server Error", "Response was not successful")
            }
        }

        override fun onFailure(call: Call<List<String>>, t: Throwable) {
            Log.e("Server Error", "Failed to connect to server: ${t.message}")
        }
    })
    return true;
}
fun create_routin(
    routin_num : Int,
    routin_name : String,
    select_days : MutableList<Boolean>,
    startHour : Int,
    startMinute : Boolean,
    endHour : Int,
    endMinute : Boolean,
    ){

}
fun getDaySchedule(day:Int) : MutableList<DaySchedule>{
    var schedules : MutableList<DaySchedule> = mutableListOf()
    schedules.add(DaySchedule(mutableListOf(1, 3, 5), "일정1", 7, false, 10, false))
    schedules.add(DaySchedule(mutableListOf(0, 3), "일정2", 8, false, 9, false))
    schedules.add(DaySchedule(mutableListOf(2, 5), "일정3", 10, false, 15, false))
    schedules.add(DaySchedule(mutableListOf(6), "일정4", 7, false, 8, false))
    schedules.add(DaySchedule(mutableListOf(0, 1, 2, 3, 4, 5, 6), "일정5", 8, false, 10, false))

    var result_schedules : MutableList<DaySchedule> = mutableListOf()

    for(schedule in schedules){
        if(day in schedule.day){
            result_schedules.add(schedule)
        }
    }

    return result_schedules
}

fun existID(deviceID : String):Boolean{
    // 해당 아이디가 등록된 장치의 아이디인지 확인
    return true
}

fun wifiLink():Boolean{
    // 기기가 정상적으로 와이파이 연결되었는지 확인
    return true
}

fun selectiedSchedule():List<Boolean>{
    // 아침, 점심, 저녁은 한 요일에 하나만 설정 가능하도록
    return listOf(false, true, false)
}

fun getMedication(meal_time : Int) : MutableList<Medication>{
    var medications : MutableList<Medication> = mutableListOf()
    medications.add(Medication(mutableListOf(0, 1, 2, 3, 4, 5, 6), "약1", 1,0, false))
    medications.add(Medication(mutableListOf(0, 2, 4, 6), "약2", 2,2, false))
    medications.add(Medication(mutableListOf(0, 1, 2, 5, 6), "약3", 2,1, false))
    medications.add(Medication(mutableListOf(0, 1, 2, 3, 4), "약4", 0,0, false))
    medications.add(Medication(mutableListOf(0, 1, 2, 3, 4, 5, 6), "약5", 1, 1, false))
    medications.add(Medication(mutableListOf(3, 4, 5, 6), "약6", 2, 0,false))

    var result_medications : MutableList<Medication> = mutableListOf()

    for(medication in medications){
        if(meal_time==medication.meal_time){
            result_medications.add(medication)
        }
    }

    return result_medications

}
