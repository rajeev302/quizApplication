package com.example.quizapplication.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import com.example.quizapplication.model.QuizModel
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.StringWriter
import kotlin.coroutines.CoroutineContext

class MainViewmodel(application: Application): AndroidViewModel(application),
    CoroutineScope {
    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    val quizDataList: MutableList<QuizModel> = mutableListOf()

    fun getQuizDataList(context: Context):List<QuizModel> {
//        val inputStream =
//            HGApplication.application.resources.openRawResource(com.homegenius.android.R.raw.time_zone_information)
        val inputStream = context.resources.openRawResource(com.example.quizapplication.R.raw.question)

        val writer = StringWriter()
        val buffer = CharArray(1024)
        try {
            val reader = BufferedReader(InputStreamReader(inputStream, "UTF-8"))
            var n: Int
            while (true) {
                n = reader.read(buffer)
                if (n == -1) {
                    break
                }
                writer.write(buffer, 0, n)

            }
        } finally {
            inputStream.close()
        }
        val moshi = Moshi.Builder().build()
        val listMyData = Types.newParameterizedType(List::class.java, QuizModel::class.java)
        val jsonAdapter: JsonAdapter<List<QuizModel>> = moshi.adapter(listMyData)
        val quizList = jsonAdapter.fromJson(writer.toString())?: listOf()
        quizDataList.addAll(quizList)
        return quizList
    }
}