package com.example.myrecyclerview

import android.content.Intent
import android.net.http.HttpException
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.ext.SdkExtensions
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity(), RecyclerViewClickListener {

    private lateinit var rvBerita: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvBerita = findViewById(R.id.rv_berita)
        rvBerita.setHasFixedSize(true)

        rvBerita.layoutManager = LinearLayoutManager(this)
        Log.d("ovin", "onCreate: Before launching coroutine")
        val bookApi = RetrofitHelper.getInstance().create(BeritaApi::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R && SdkExtensions.getExtensionVersion(
                    Build.VERSION_CODES.S) >= 7) {
                try {
                    val response = beritaApi.getBerita()
                    Log.d("ovin", "onCreate: After API call, response received")
                    withContext(Dispatchers.Main) {
                        if (response.isSuccessful) {
                            val listBeritaAdapter = ListBeritaAdapter(response.body()!!)
                            rvBerita.adapter = listBeritaAdapter
                            listBeritaAdapter.clickListener = this@MainActivity
                            Log.d("ovin", "onCreate: Adapter set successfully")
                        } else {
                            Toast.makeText(
                                this@MainActivity,
                                "Error ${response.code()}",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                } catch (e: HttpException) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@MainActivity, "Error ${e.message}", Toast.LENGTH_LONG)
                            .show()
                    }
                } catch (t: Throwable) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            this@MainActivity,
                            "Terjadi kesalahan jaringan",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
    }

    private fun HalamanAbout(AboutActivity: Class<*>) {
        val intent = Intent(this, AboutActivity)
        startActivity(intent)
    }

    override fun onItemClicked(view: View, berita: Berita) {
        val intenDetail = Intent(this,DetailActivity::class.java)
        intenDetail.putExtra("extra_detail",berita)
        startActivity(intenDetail)
    }




}