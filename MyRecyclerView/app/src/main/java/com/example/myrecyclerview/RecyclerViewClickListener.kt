package com.example.myrecyclerview

import android.view.View
interface RecyclerViewClickListener {

    // method yang akan dipanggil di MainActivity
    fun onItemClicked(view: View, berita: Berita)

}
