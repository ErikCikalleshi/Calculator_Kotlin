package com.example.test

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import java.util.ArrayList

class MainActivity2 : AppCompatActivity() {

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val receivedData : ArrayList<String>? = intent.getStringArrayListExtra("data")
        val sw_save : Boolean = intent.getBooleanExtra("sw_switch", true)

        if(receivedData != null){
            for (string in receivedData){
                val value = TextView(this)
                value.textSize = 15f
                value.text = string
                findViewById<LinearLayout>(R.id.linear).addView(value)
            }
        }

        findViewById<Button>(R.id.button2).setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("data", receivedData)
            intent.putExtra("sw_save", sw_save)
            startActivity(intent)
        }
    }


}

