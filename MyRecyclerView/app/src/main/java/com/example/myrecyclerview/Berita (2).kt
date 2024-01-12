package com.example.myrecyclerview

import retrofit2.Response
import retrofit2.http.GET

    interface BookApi {

        @GET("book.json")
        suspend fun getBooks() : Response<List<Book>>
    }