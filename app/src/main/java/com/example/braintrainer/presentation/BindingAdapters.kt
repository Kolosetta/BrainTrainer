package com.example.braintrainer.presentation

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.braintrainer.R

@BindingAdapter("requiredAnswers")
fun bindRequireAnswers(textView: TextView, count: Int){
    textView.text = String.format(
        textView.context.getString(R.string.required_answers_label),
        count
    )
}