package com.novelitech.sharedpreferenceskotlin

import android.content.Context
import android.hardware.display.DisplayManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import com.novelitech.sharedpreferenceskotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        /**
         * There are 3 different modes that I can apply on my SharedPreferences
         * 1 - More public. Every other app can access the SharedPreferences from this app
         * 2 - More private. No other app can access the SharedPreferences from this app
         * 3 - Depends. It will take existing preferences, so it will override them
         */
        val sharedPreference = getSharedPreferences("myPref", Context.MODE_PRIVATE)

        val editor = sharedPreference.edit()

        binding.btnSave.setOnClickListener {
            val name = binding.etName.text.toString()
            val age = binding.etAge.text.toString().toInt()
            val isAdult = binding.cbAdult.isChecked

            editor.apply {
                putString("SP_NAME", name)
                putInt("SP_AGE", age)
                putBoolean("SP_ADULT", isAdult)
                apply() // I finish and I want to write the data into the Shared Preferences
                // commit() // It will do the same as apply(), but synchronously, which would freeze the screen
            }
        }

        binding.btnLoad.setOnClickListener {

            val name = sharedPreference.getString("SP_NAME", "")
            val age = sharedPreference.getInt("SP_AGE", 0)
            val isAdult = sharedPreference.getBoolean("SP_ADULT", false)

            binding.etName.setText(name)
            binding.etAge.setText(age.toString())
            binding.cbAdult.isChecked = isAdult
        }
    }
}