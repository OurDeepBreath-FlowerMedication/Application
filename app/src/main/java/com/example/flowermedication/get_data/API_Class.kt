package com.example.flowermedication.get_data

data class MealResponse(
    val meal_time: List<Boolean>
)

data class RoutinData(
    val routin_num: Int,
    val routin_name: String,
    val select_days: List<Int>,
    val start_hour: Int,
    val start_minute: Boolean,
    val end_hour: Int,
    val end_minute: Boolean
)

data class MedicationData(
    var day: List<Int>,
    var medication_name: String,
    var meal_time : Int,
    val interval : Int,
    val use_device: Boolean
)

data class RoutinDelRequest(
    val deviceID: String,
    val id: Int
)

data class MedicationDelRequest(
    val deviceID: String,
    val id: Int
)

