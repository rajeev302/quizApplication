package com.example.quizapplication.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class QuizModel(
    @Json(name = "option1")
    var option1: String?,
    @Json(name = "option2")
    var option2: String?,
    @Json(name = "option3")
    var option3: String?,
    @Json(name = "option4")
    var option4: String?,
    @Json(name = "question")
    var question: String?,
    @Json(name = "selectedAnswer")
    var selectedAnswer: String? = null
)