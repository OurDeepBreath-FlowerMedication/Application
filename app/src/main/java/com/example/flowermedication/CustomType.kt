package com.example.flowermedication

data class DayItem(
    var date: String,
    var progress: String,
    var details: MutableList<DetailItem> = mutableListOf()
)

data class DetailItem(
    var activity: String,
    var time: String
)

data class TodaySchedule(
    var schedule_name: String,
    var doneTime : String,
    var isDone : Boolean
)

data class DaySchedule(
    var day: List<Int>,
    var schedule_name: String,
    var start_hour: Int,
    var start_minute: Boolean,
    var end_hour: Int,
    var end_minute: Boolean,
    var id : Int
)

data class Medication(
    var day: List<Int>,
    var medication_name: String,
    var meal_time : Int,
    val interval : Int,
    val use_device: Boolean,
    val id : Int
)