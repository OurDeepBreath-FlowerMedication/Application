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

data class Schedule(
    var content : String,
    var year : Int = 0,
    var month : Int = 0,
    var day : Int = 0,
    var hour : Int = 0,
    var minute : Int = 0,
    var done : Boolean = false
)

data class DaySchedule(
    var day: MutableList<Int> = mutableListOf(),
    var schedule_name: String,
    var start_hour: Int,
    var start_minute: Boolean,
    var end_hour: Int,
    var end_minute: Boolean
)

data class Medication(
    var day: MutableList<Int> = mutableListOf(),
    var medication_name: String,
    var meal_time : Int,
    val interval : Int,
    val with_device: Boolean
)