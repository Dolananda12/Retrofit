package com.example.retrofit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModelClass() :ViewModel() {
    var AnswerButton : MutableLiveData<String> = MutableLiveData()
    var QuestionButton :MutableLiveData<String> = MutableLiveData()
    var ModeButton :MutableLiveData<String> = MutableLiveData()

    var mode : Int =0
    private lateinit var answer :String
    private lateinit var question :String

    fun storeAnswer(ans : String,que:String){
        println("came into view model")
        answer=ans
        question=que
        QuestionButton.value=que
        AnswerButton.value="Click to Know Ans"
    }
    fun newThought(quote : String){
        QuestionButton.value=quote
        AnswerButton.value="next Quote"
    }
    init{
        AnswerButton.value=""
        QuestionButton.value="Click for a Joke!!"
        ModeButton.value="Click for Quotes"
        mode=0

    }
    fun onToggle(){
        mode++;
        if(mode%2!=0){
            QuestionButton.value="New Quote"
            AnswerButton.value=""
            ModeButton.value="Click for Jokes"
        //quotes
        }else{
            AnswerButton.value=""
            QuestionButton.value="Click for a Joke!!"
            ModeButton.value="Click for Quotes"
        }
    }
    fun onButtontouch_Answer() {
        if (mode % 2 == 0) {
            AnswerButton.value = answer
            QuestionButton.value = "Click for another joke!"
        }
    }
}