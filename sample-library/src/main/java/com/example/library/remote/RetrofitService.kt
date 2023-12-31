package com.example.library.remote

import retrofit2.Response
import retrofit2.http.GET


interface RetrofitService {
    @GET("/quotes")
    suspend fun getQuotes() : Response<QuoteList>
}
