package com.example.versioncatalogsample.remote

import com.example.versioncatalogsample.remote.utils.BaseRemoteDataSource
import com.example.versioncatalogsample.remote.utils.Resource
import com.example.versioncatalogsample.remote.utils.safeApiCall

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