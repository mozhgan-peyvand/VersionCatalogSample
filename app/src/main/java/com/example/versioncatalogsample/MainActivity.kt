package com.example.versioncatalogsample

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.versioncatalogsample.remote.RemoteDataSource
import com.example.versioncatalogsample.remote.Result
import com.example.versioncatalogsample.remote.RetrofitHelper
import com.example.versioncatalogsample.remote.RetrofitService
import com.example.versioncatalogsample.remote.utils.Exceptions
import com.example.versioncatalogsample.remote.utils.NetworkHandler
import com.example.versioncatalogsample.remote.utils.Resource
import com.example.versioncatalogsample.ui.theme.VersionCatalogSampleTheme
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    private val remoteDataSource =
        RemoteDataSource(RetrofitHelper.getInstance().create(RetrofitService::class.java))

    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val coroutineScope = rememberCoroutineScope()
            val resultList = remember { mutableStateListOf<Result>() }


            VersionCatalogSampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
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
                                Text(text = it.authorSpecial?: "")
                            }
                            Divider(modifier = Modifier.height(4.dp))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    VersionCatalogSampleTheme {
        Greeting("Android")
    }
}