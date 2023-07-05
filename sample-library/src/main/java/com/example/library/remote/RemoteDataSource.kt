package com.example.library.remote

import com.example.library.remote.utils.BaseRemoteDataSource
import com.example.library.remote.utils.Resource
import com.example.library.remote.utils.safeApiCall

class RemoteDataSource constructor(
    private val retrofitService: RetrofitService
) {

    private val baseRemoteDataSource = BaseRemoteDataSource()

    suspend fun getQuotesList() : Resource<QuoteList> = safeApiCall(
        call = { requestQuotesList() },
        errorMessage = "error message for quotes"
    )

    private suspend fun requestQuotesList(): Resource<QuoteList> {
        return baseRemoteDataSource.checkApiResult(
            response = retrofitService.getQuotes()
        )
    }
}