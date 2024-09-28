package com.example.flowermedication.get_data

import com.example.flowermedication.DaySchedule
import com.example.flowermedication.Medication
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

// API 인터페이스 정의
interface MealCheck {
    @GET("/routin/meal")
    //http://주소/create_routin/meal/?deviceID ... 전달
    suspend fun getData(@Query("deviceID") deviceID: String): MealResponse
}
interface RoutinCreate {
    @POST("/routin/create")
    suspend fun createRoutin(@Query("deviceID") deviceID: String, @Body routinData: RoutinData): Call<List<String>>
}

interface RoutinGet {
    @GET("/routin/get")
    suspend fun getData(@Query("deviceID") deviceID: String): Response<List<Map<String, Any>>>
}

interface RoutinDel {
    @POST("/routin/delete")
    fun delRoutin(@Body delID : RoutinDelRequest) : Call<List<String>>
}

interface MedicationCreate {
    @POST("/medication/create")
    suspend fun createMedication(@Query("deviceID") deviceID: String, @Body mediData: MedicationData): Call<List<String>>
}

interface MedicationGet {
    @GET("/medication/get")
    suspend fun getData(@Query("deviceID") deviceID: String): Response<List<Map<String, Any>>>
}

interface MedicationDel {
    @POST("/medication/delete")
    fun delMedication(@Body delID : MedicationDelRequest) : Call<List<String>>
}

interface MedicationCheck {
    @GET("/medication/check")
    suspend fun getData(@Query("deviceID") deviceID: String, @Query("mealTime") mealTime: Int): Response<List<Boolean>>
}

interface TodayGet {
    @GET("/done/get")
    suspend fun getData(@Query("deviceID") deviceID: String): Response<List<Map<String, Any>>>
}