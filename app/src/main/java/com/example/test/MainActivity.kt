package com.example.test

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.core.widget.doOnTextChanged
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    private var id: Int = 0 //id to get the selected radio-button from the radio-group
    private var list = arrayListOf<String>() //each calculation is in a ArrayList saved
    private var sw_save: Boolean = false

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //initialize
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val input1 = findViewById<TextView>(R.id.nr1)
        val input2 = findViewById<TextView>(R.id.nr2)
        val switch = findViewById<Switch>(R.id.switch1)
        val button = findViewById<Button>(R.id.button)

        //when returning back to Activity Main
        reload(switch)

        //Actions
        onActions(radioGroup, input1, input2, switch, button)


    }

    private fun reload(@SuppressLint("UseSwitchCompatOrMaterialCode") switch: Switch) {
        val receivedData : ArrayList<String>? = intent.getStringArrayListExtra("data")
        if(receivedData != null){
            list = receivedData
            sw_save = intent.getBooleanExtra("sw_save", true)
            switch.isChecked = sw_save
        }
    }

    private fun onActions(radioGroup: RadioGroup, input1: TextView, input2: TextView, @SuppressLint("UseSwitchCompatOrMaterialCode") switch: Switch, button: Button) {
        //History-Button
        button.setOnClickListener{
            val intent = Intent(this, MainActivity2::class.java)
            intent.putExtra("data", list)
            intent.putExtra("sw_save", sw_save)
            startActivity(intent)
        }

        //setOnAction...
        switch.setOnCheckedChangeListener{_, isChecked ->
            sw_save = isChecked
        }
        radioGroup.setOnCheckedChangeListener { _, _ ->
            id = radioGroup.checkedRadioButtonId
            operation()
        }

        input1.doOnTextChanged { _, _, _, _->
            operation()
        }
        input2.doOnTextChanged { _, _, _, _->
            operation()
        }
    }

    private fun operation() {
        val result = findViewById<TextView>(R.id.res)
        var res = 0.0
        try {
            when (findViewById<RadioButton>(id).text) {
                "Addition" -> {
                    res = findViewById<TextView>(R.id.nr1).text.toString().toDouble() + findViewById<TextView>(R.id.nr2).text.toString().toDouble()
                    save("+", res)

                }
                "Subtraction" -> {
                    res = findViewById<TextView>(R.id.nr1).text.toString().toDouble() - findViewById<TextView>(R.id.nr2).text.toString().toDouble()
                    save("-", res)

                }
                "Multiplication" -> {
                    res = findViewById<TextView>(R.id.nr1).text.toString().toDouble() * findViewById<TextView>(R.id.nr2).text.toString().toDouble()
                    save("*", res)

                }
                "Division" -> {
                    res = findViewById<TextView>(R.id.nr1).text.toString().toDouble() / findViewById<TextView>(R.id.nr2).text.toString().toDouble()
                    save("/", res)
                }
            }
            result.text = res.toString()
        } catch (e: Exception) {
            println(e)
        }

    }

    private fun save(s: String, res: Double) {
        if (sw_save){
            list.add(findViewById<TextView>(R.id.nr1).text.toString() + s + findViewById<TextView>(R.id.nr2).text.toString() + " = " + res.toString())
        }
    }
}

