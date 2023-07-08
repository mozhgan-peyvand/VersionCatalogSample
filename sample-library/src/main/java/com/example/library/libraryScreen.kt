package com.example.library

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.library.remote.RemoteDataSource
import com.example.library.remote.Result
import com.example.library.remote.RetrofitHelper
import com.example.library.remote.RetrofitService
import com.example.library.remote.utils.Exceptions
import com.example.library.remote.utils.NetworkHandler
import com.example.library.remote.utils.Resource
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun LibraryScreen() {

    val coroutineScope = rememberCoroutineScope()
    val resultList = remember { mutableStateListOf<Result>() }
    val remoteDataSource =
        RemoteDataSource(RetrofitHelper.getInstance().create(RetrofitService::class.java))



    val networkHandler = NetworkHandler(LocalContext.current)
    // launching a new coroutine
    coroutineScope.launch {
        if (networkHandler.hasNetworkConnection()) {
            when (val result = remoteDataSource.getQuotesList()) {
                is Resource.Success -> {
                    Log.i("TAG", result.data.toString())
                    resultList.addAll(result.data.results)
                }

                is Resource.Error -> Resource.Error(
                    Exceptions.RemoteDataSourceException(
                        result.error.toString()
                    )
                )
            }
        } else
            Resource.Error(Exceptions.NetworkConnectionException())
    }
    LazyColumn(modifier = Modifier.fillMaxWidth()) {

        items(resultList) {
            Column(modifier = Modifier.padding(horizontal = 8.dp)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = it.author)
                    Text(text = it.dateAdded)
                }
                Text(text = it.authorSpecial ?: "")
            }
            Divider(modifier = Modifier.height(4.dp))
        }
    }
//            }
}