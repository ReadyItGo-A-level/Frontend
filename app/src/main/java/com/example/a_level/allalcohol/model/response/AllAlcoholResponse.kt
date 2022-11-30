package com.example.a_level.allalcohol.model.response

data class AllAlcoholResponse(
    val status: Int,
    val message: String,
    val data: Data
)

data class Data(
    val alcohol: List<Alcohol>,
    val total: Int
)
