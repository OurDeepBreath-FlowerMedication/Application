package com.example.flowermedication.get_data

import android.util.Log
import android.widget.Toast
import com.example.flowermedication.DaySchedule
import com.example.flowermedication.Medication
import com.example.flowermedication.TodaySchedule

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import com.example.flowermedication.get_data.*
import com.google.gson.Gson

object ServerIP{
    // 서버 설정
    val IP : String = "http://10.0.2.2:3000/"
}

//임시
object DeviceID{
    // 서버 설정
    val ID : String = "534er24333dd"
}

suspend fun routin_meal():List<Boolean>?{
    // Retrofit 인스턴스 => http통신을 위한 라이브러리
    val retrofit = Retrofit.Builder()
        .baseUrl(ServerIP.IP)
        .addConverterFactory(GsonConverterFactory.create()) // JSON -> 객체 변환
        .build()

    // API 서비스 생성
    val mealCheck = retrofit.create(MealCheck::class.java)

    return try{
        mealCheck.getData(DeviceID.ID).meal_time
    }catch (e: Exception){
        Log.e("Server Error", "Failed to connect to server: ${e.message}")
        null
    }
}
fun create_routin(
    routin_num : Int,
    routin_name : String,
    select_days : MutableList<Boolean>,
    start_hour : Int,
    start_minute : Boolean,
    end_hour : Int,
    end_minute : Boolean,
    ){

    val retrofit = Retrofit.Builder()
        .baseUrl(ServerIP.IP)
        .addConverterFactory(GsonConverterFactory.create()) // JSON -> 객체 변환
        .build()

    // API 서비스 생성
    val routinCreate = retrofit.create(RoutinCreate::class.java)

    val routinData = RoutinData(
        routin_num = routin_num,
        routin_name = routin_name,
        select_days = select_days.map { if (it) 1 else 0 },
        start_hour = start_hour,
        start_minute = start_minute,
        end_hour = end_hour,
        end_minute = end_minute
    )

    routinCreate.createRoutin(DeviceID.ID, routinData).enqueue(object : Callback<List<String>> {
        override fun onResponse(
            call: Call<List<String>>,
            response: Response<List<String>>
        ) {
            if (response.isSuccessful) {
                val result = response.body()
                println("서버 응답: $result")
            } else {
                println("서버 에러: ${response.errorBody()}")
            }
        }

        override fun onFailure(call: Call<List<String>>, t: Throwable) {
            println("통신 실패: ${t.message}")
        }
    })
}
suspend fun getDaySchedule() : MutableList<DaySchedule>{
    val retrofit = Retrofit.Builder()
        .baseUrl(ServerIP.IP)
        .addConverterFactory(GsonConverterFactory.create()) // JSON -> 객체 변환
        .build()

    // API 서비스 생성
    val routins = retrofit.create(RoutinGet::class.java)
    var schedules : MutableList<DaySchedule> = mutableListOf()

    return try{
        val response = routins.getData(DeviceID.ID)
        if (response.isSuccessful && response.body() != null) {
            val responseData = response.body()!!
            for(item in responseData){
                var days = (item["day"] as String).split(',')
                schedules.add(
                    DaySchedule(mutableListOf(days[0].toInt(), days[1].toInt(), days[2].toInt(), days[3].toInt(), days[4].toInt(), days[5].toInt(), days[6].toInt()),
                    item["schedule_name"] as String, (item["start_hour"] as Double).toInt(), item["start_minute"] as Boolean, (item["end_hour"] as Double).toInt(), item["end_minute"] as Boolean, (item["id"] as Double).toInt()))
            }

        } else {
            // 에러 처리: 서버에서 오류 코드 반환 시 로그로 출력
            Log.e("Server Error", "Error: ${response.code()} - ${response.message()}")
        }
        schedules
    }catch (e: Exception){
        Log.e("Server Error", "Failed to connect to server: ${e.message}")
        schedules
    }
}

fun delRoutin(position : Int){
    val retrofit = Retrofit.Builder()
        .baseUrl(ServerIP.IP)
        .addConverterFactory(GsonConverterFactory.create()) // JSON -> 객체 변환
        .build()

    // API 서비스 생성
    val routinDelete = retrofit.create(RoutinDel::class.java)
    val delData = RoutinDelRequest(
        deviceID = DeviceID.ID,
        id = position
    )
    routinDelete.delRoutin(delData).enqueue(object : Callback<List<String>> {
        override fun onResponse(
            call: Call<List<String>>,
            response: Response<List<String>>
        ) {
            if (response.isSuccessful) {
                val result = response.body()
                println("서버 응답: $result")
            } else {
                println("서버 에러: ${response.errorBody()}")
            }
        }

        override fun onFailure(call: Call<List<String>>, t: Throwable) {
            println("통신 실패: ${t.message}")
        }
    })
}

fun create_medication(
    select_days: MutableList<Boolean>,
    medication_name : String,
    meal_time : Int,
    interval : Int,
    use_device : Boolean
){

    val retrofit = Retrofit.Builder()
        .baseUrl(ServerIP.IP)
        .addConverterFactory(GsonConverterFactory.create()) // JSON -> 객체 변환
        .build()

    // API 서비스 생성
    val routinCreate = retrofit.create(MedicationCreate::class.java)

    val medicationData = MedicationData(
        day = select_days.map { if (it) 1 else 0 },
        medication_name = medication_name,
        meal_time = meal_time,
        interval = interval,
        use_device = use_device
    )

    routinCreate.createMedication(DeviceID.ID, medicationData).enqueue(object : Callback<List<String>> {
        override fun onResponse(
            call: Call<List<String>>,
            response: Response<List<String>>
        ) {
            if (response.isSuccessful) {
                val result = response.body()
                println("서버 응답: $result")
            } else {
                println("서버 에러: ${response.errorBody()}")
            }
        }

        override fun onFailure(call: Call<List<String>>, t: Throwable) {
            println("통신 실패: ${t.message}")
        }
    })
}

suspend fun getMedication() : MutableList<Medication>{
    val retrofit = Retrofit.Builder()
        .baseUrl(ServerIP.IP)
        .addConverterFactory(GsonConverterFactory.create()) // JSON -> 객체 변환
        .build()

    // API 서비스 생성
    val routins = retrofit.create(MedicationGet::class.java)
    var medications : MutableList<Medication> = mutableListOf()

    return try{
        val response = routins.getData(DeviceID.ID)
        if (response.isSuccessful && response.body() != null) {
            val responseData = response.body()!!
            for(item in responseData){
                var days = (item["day"] as String).split(',')
                medications.add(
                    Medication(mutableListOf(days[0].toInt(), days[1].toInt(), days[2].toInt(), days[3].toInt(), days[4].toInt(), days[5].toInt(), days[6].toInt()),
                        item["medication_name"] as String, (item["meal_time"] as Double).toInt(), (item["interval"] as Double).toInt(), item["use_device"] as Boolean, (item["id"] as Double).toInt()))
            }

        } else {
            // 에러 처리: 서버에서 오류 코드 반환 시 로그로 출력
            Log.e("Server Error", "Error: ${response.code()} - ${response.message()}")
        }
        medications
    }catch (e: Exception){
        Log.e("Server Error", "Failed to connect to server: ${e.message}")
        medications
    }
}

fun delMedication(id : Int){
    val retrofit = Retrofit.Builder()
        .baseUrl(ServerIP.IP)
        .addConverterFactory(GsonConverterFactory.create()) // JSON -> 객체 변환
        .build()

    // API 서비스 생성
    val medicationDelete = retrofit.create(MedicationDel::class.java)
    val delData = MedicationDelRequest(
        deviceID = DeviceID.ID,
        id = id
    )
    medicationDelete.delMedication(delData).enqueue(object : Callback<List<String>> {
        override fun onResponse(
            call: Call<List<String>>,
            response: Response<List<String>>
        ) {
            if (response.isSuccessful) {
                val result = response.body()
                println("서버 응답: $result")
            } else {
                println("서버 에러: ${response.errorBody()}")
            }
        }

        override fun onFailure(call: Call<List<String>>, t: Throwable) {
            println("통신 실패: ${t.message}")
        }
    })
}

suspend fun getTodaySchedule() : MutableList<TodaySchedule>{
    val retrofit = Retrofit.Builder()
        .baseUrl(ServerIP.IP)
        .addConverterFactory(GsonConverterFactory.create()) // JSON -> 객체 변환
        .build()

    // API 서비스 생성
    val todayGet = retrofit.create(TodayGet::class.java)
    var todaySchedule : MutableList<TodaySchedule> = mutableListOf()

    return try{
        val response = todayGet.getData(DeviceID.ID)
        if (response.isSuccessful && response.body() != null) {
            val responseData = response.body()!!
            for(item in responseData){
                todaySchedule.add(
                    TodaySchedule(item["schedule_name"] as String, item["doneTime"] as String, item["isDone"] as Boolean)
                )
            }

        } else {
            // 에러 처리: 서버에서 오류 코드 반환 시 로그로 출력
            Log.e("Server Error", "Error: ${response.code()} - ${response.message()}")
        }
        todaySchedule
    }catch (e: Exception){
        Log.e("Server Error", "Failed to connect to server: ${e.message}")
        todaySchedule
    }
}


fun existID(deviceID : String):Boolean{
    // 해당 아이디가 등록된 장치의 아이디인지 확인
    return true
}

fun wifiLink():Boolean{
    // 기기가 정상적으로 와이파이 연결되었는지 확인
    return true
}