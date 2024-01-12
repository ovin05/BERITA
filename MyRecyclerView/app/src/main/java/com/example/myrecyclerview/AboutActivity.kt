package com.example.myrecyclerview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
         val backMenu = findViewById<Button>(R.id.btnBack)
        backMenu.setOnClickListener {
            BackToMenu(MainActivity::class.java)
        }
    }
    private fun BackToMenu(menu: Class<*>) {
        val intent = Intent(this, menu)
        startActivity(intent)
    }
}