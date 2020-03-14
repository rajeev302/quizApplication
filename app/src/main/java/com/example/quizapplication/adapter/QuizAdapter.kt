package com.example.quizapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapplication.R
import com.example.quizapplication.model.QuizModel

class QuizAdapter(
    val context: Context,
    var isEditMode: Boolean,
    val dataSet: MutableList<QuizModel>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int {
        return dataSet.count()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val quizHolder = holder as QuizListViewHolder
        quizHolder.question.text = dataSet[position].question
        if (isEditMode){
            quizHolder.selected_answer.visibility = View.GONE
            quizHolder.radioGroup.visibility = View.VISIBLE
            quizHolder.option_one.text = dataSet[position].option1
            quizHolder.option_two.text = dataSet[position].option2
            quizHolder.option_three.text = dataSet[position].option3
            quizHolder.option_fourth.text = dataSet[position].option4
        } else {
            quizHolder.selected_answer.visibility = View.VISIBLE
            quizHolder.radioGroup.visibility = View.GONE
            quizHolder.selected_answer.text = dataSet[position].selectedAnswer
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_quiz_row, parent, false)
        return QuizListViewHolder(itemView)
    }

    inner class QuizListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val question: TextView = itemView.findViewById(R.id.row_question)
        val radioGroup: RadioGroup = itemView.findViewById(R.id.options_radio_group)
        val option_one: RadioButton = itemView.findViewById(R.id.row_option_1)
        val option_two: RadioButton = itemView.findViewById(R.id.row_option_2)
        val option_three: RadioButton = itemView.findViewById(R.id.row_option_3)
        val option_fourth: RadioButton = itemView.findViewById(R.id.row_option_4)
        val selected_answer: TextView = itemView.findViewById(R.id.selected_answer)
        init {
            option_one.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked){
                    dataSet.getOrNull(adapterPosition)?.let {
                        it.selectedAnswer = it.option1
                    }
                }
            }
            option_two.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked){
                    dataSet.getOrNull(adapterPosition)?.let {
                        it.selectedAnswer = it.option2
                    }
                }
            }
            option_three.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked){
                    dataSet.getOrNull(adapterPosition)?.let {
                        it.selectedAnswer = it.option3
                    }
                }
            }
            option_fourth.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked){
                    dataSet.getOrNull(adapterPosition)?.let {
                        it.selectedAnswer = it.option4
                    }
                }
            }
        }
    }
}