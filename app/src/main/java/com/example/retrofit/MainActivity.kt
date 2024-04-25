package com.example.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Spinner
import android.widget.Toast
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.retrofit.databinding.ActivityMainBinding
import retrofit2.Response
import java.lang.Exception

class MainActivity : AppCompatActivity() {
     private lateinit var viewModelClass: ViewModelClass
     private lateinit var binding: ActivityMainBinding
     private lateinit var jokeservice :JokesINterface
    private lateinit var thoughservice :GreatThoughInterface
     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         binding=ActivityMainBinding.inflate(layoutInflater)
         binding.lifecycleOwner=this
         setContentView(binding.root)
         viewModelClass=ViewModelProvider(this).get(ViewModelClass::class.java)
         binding.myViewModel=viewModelClass
         jokeservice = RetrofitInstance
             .getRetrofitInstance_1().create(JokesINterface::class.java)
         thoughservice = RetrofitInstance
             .getRetrofitInstance_2().create(GreatThoughInterface::class.java)
         binding.question.setOnClickListener {
             giveJoke()
         }
         binding.answer.setOnClickListener {
             giveJoke()
             if(viewModelClass.mode%2!=0){

             }else{

             }
         }
         binding.view.setOnClickListener{
             viewModelClass.onToggle()
             giveJoke()
         }
    }
    private fun giveJoke(){
        binding.progressBar.visibility=View.VISIBLE
        binding.question.text=""
        binding.answer.text=""
        if(viewModelClass.mode%2==0) {
            val responseLiveData: LiveData<Response<jokes>> = liveData {
                try {
                    val response: Response<jokes> = jokeservice.getJoke()
                    emit(response)
                } catch (e: Exception) {
                    Toast.makeText(this@MainActivity, "session timeout", Toast.LENGTH_SHORT).show()
                    binding.question.text = "Click for a Joke!"
                    binding.progressBar.visibility = View.GONE
                    e.printStackTrace()
                }

            }
            responseLiveData.observe(this, Observer {
                val ans = it.body()!!.punchline
                val que = it.body()!!.setup
                binding.progressBar.visibility = View.GONE
                viewModelClass.storeAnswer(ans, que)
            })
        }else{
            val responseLiveData: LiveData<Response<ArrayList<greatThoughtsItem>>> = liveData {
                try {
                    val response: Response<ArrayList<greatThoughtsItem>> = thoughservice.newQuote()
                    println(response)
                    emit(response)
                } catch (e: Exception) {
                    Toast.makeText(this@MainActivity, "session timeout", Toast.LENGTH_SHORT).show()
                    viewModelClass.mode++;
                    binding.question.text = "Click for a Joke!"
                    binding.progressBar.visibility = View.GONE
                    e.printStackTrace()
                }
            }
            responseLiveData.observe(this, Observer {
                binding.progressBar.visibility = View.GONE
                val sb : StringBuilder = StringBuilder()
                for (i in 19..(it!!.body()!!.get(0).h.length ?: 19)) {
                    if (it!!.body()!!.get(0).h.get(i) != '&') {
                        sb.append(it!!.body()!!.get(0).h.get(i))
                    }else{
                        break
                    }
                }
                viewModelClass.newThought(sb.toString())
            })
        }
    }
}