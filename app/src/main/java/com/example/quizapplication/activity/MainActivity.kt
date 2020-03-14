package com.example.quizapplication.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapplication.R
import com.example.quizapplication.adapter.QuizAdapter
import com.example.quizapplication.viewmodel.MainViewmodel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var viewmodel: MainViewmodel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: QuizAdapter
    private lateinit var submitButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewmodel = ViewModelProvider(this).get(MainViewmodel::class.java)
        viewmodel.getQuizDataList(this)
        submitButton = submit_button
        setupRecyclerView()
        setupClickListener()
    }

    private fun setupRecyclerView(){
        recyclerView = quiz_recycler_view
        adapter = QuizAdapter(this, true, viewmodel.quizDataList)
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }

    private fun setupClickListener(){
        submitButton.setOnClickListener {
            adapter.isEditMode = false
            adapter.notifyDataSetChanged()
        }
    }
}
