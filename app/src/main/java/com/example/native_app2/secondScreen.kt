package com.example.native_app2

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.native_app2.databinding.ActivitySecondScreenBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.URL
import kotlin.math.roundToInt

class secondScreen : AppCompatActivity() {
    lateinit var binding: ActivitySecondScreenBinding
    lateinit var cityName:String
    lateinit var response:String
    val ApiKey:String="APIKEY"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivitySecondScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val intent = getIntent()
        cityName = intent.getStringExtra("cityName").toString()
        print("xxxxxxxxxxxxxxxxxxxxxxxxxxxxx")
        println(cityName)
        binding.textView5.text = cityName
        supportActionBar?.hide()
        execute()
    }
    fun execute()= runBlocking{
        println("e1")
        withContext(Dispatchers.Default) { toggleUi1() }
        println("e1")
        withContext(Dispatchers.Default) { getDataApi() }
        println("e1")
        withContext(Dispatchers.Default) { toggleUi12() }
        println("e1")

        withContext(Dispatchers.Default) { setData() }
        println("e1")
    }
    fun toggleUi1(){
        println("t1")

        println("t1")
        binding.progressBar.visibility = View.VISIBLE
        println("t1")
    }
    suspend fun getDataApi(){
        println("g1")
        response= URL("https://api.openweathermap.org/data/2.5/weather?q=${cityName}&units=metric&appid=${ApiKey}").readText(Charsets.UTF_8)
        println(response)
        println("g1")
    }
    fun toggleUi12(){
        println("t1")

        println("t1")
        binding.progressBar.visibility = View.GONE
        println("t1")
    }
    fun setData(){
        println("s1")
        var json= JSONObject(response)
        println("s1")
        var weather = json.getJSONArray("weather")
        var main= json.getJSONObject("main")
        var temp:String = main.getString("temp").toDouble().roundToInt().toString() +"°C"
        var tempMin:String = main.getString("temp_min").toDouble().roundToInt().toString() +"°C"
        var tempMax:String = main.getString("temp_max").toDouble().roundToInt().toString() +"°C"
        var  weathertype:String = weather.getJSONObject(0).getString("main")
        var humidity:String = main.getString("humidity").toDouble().roundToInt().toString() +"%"
        var visibility:Int = json.getString("visibility").toDouble().roundToInt() / 1000
        var vis:String= visibility.toString()
        var id:Int= weather.getJSONObject(0).getString("id").toInt()
        var wind:String = json.getJSONObject("wind").getString("speed").toDouble().roundToInt().toString()
        var city:String=  json.getString("name")
        println("s1")

        binding.textView4.text = temp
        binding.hightemp.text= tempMax
        binding.lowtemp.text= tempMin
        binding.humidity.text= humidity
        binding.visibility.text = vis
        binding.weatherdata.text= weathertype
        binding.wind.text = wind
        binding.textView5.text = city
        if(id>=200 && id<300){
            binding.imageView.setImageResource(R.drawable.thunder)
        }
        else if(id>=300 && id<322){
            binding.imageView.setImageResource(R.drawable.drizzle)
        }
        else if(id>=500 && id<=531){
            binding.imageView.setImageResource(R.drawable.rain)
        }
        else if(id>=600 && id<=622){
            binding.imageView.setImageResource(R.drawable.snow)
        }
        else if(id==800){
            binding.imageView.setImageResource(R.drawable.sunny)
        }
        else if(id>=701&&id<=781){
            binding.imageView.setImageResource(R.drawable.atm)
        }
        else if(id>800 && id<=804){
            binding.imageView.setImageResource(R.drawable.cloudy)
        }

        println("s1")

    }
}