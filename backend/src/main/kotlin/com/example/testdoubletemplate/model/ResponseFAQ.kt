package com.example.testdoubletemplate.model

data class ResponseFAQ(
    val category: String,
    val questionAndAnswerList: List<QuestionAndAnswer>
)

data class QuestionAndAnswer(
    val question: String,
    val answer: String,
    val answerCreateAt: String,
    val questionCreateAt: String
)
