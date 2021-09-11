package com.example.native_app2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.native_app2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.hide()

    }

    fun nextpage(view: View) {
        val cityName:String= binding.editTextTextPersonName.text.toString()
        print("xxxxxxxxxxxxxxxxxxxxxxxxxxxxx")
        println(cityName)
        val intent = Intent(this@MainActivity,secondScreen::class.java)
        intent.putExtra("cityName",cityName)
        startActivity(intent)
    }
}