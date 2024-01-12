package com.example.myrecyclerview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val image = findViewById<ImageView>(R.id.imageView)
        val judul = findViewById<TextView>(R.id.judul)
        val dec = findViewById<TextView>(R.id.description)
        val dataIntent = intent.getParcelableExtra<Berita>("extra_detail")

        Glide.with(this)
            .load(dataIntent?.photo)
            .into(image)
            judul.text =  dataIntent?.name
            dec.text = dataIntent?.description

    }
}