package com.example.myrecyclerview

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Berita(
    @SerializedName("title")
    val name: String,
    @SerializedName("desc")
    val description: String,
    @SerializedName("image")
    val photo: String
) : Parcelable
