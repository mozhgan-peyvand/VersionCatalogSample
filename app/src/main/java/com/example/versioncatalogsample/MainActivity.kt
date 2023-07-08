package com.example.versioncatalogsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.base.Screen
import com.example.library.LibraryScreen
import com.example.versioncatalogsample.ui.theme.VersionCatalogSampleTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            VersionCatalogSampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = Screen.SampleLibrary.router
                    ) {

                        composable(route = Screen.SampleLibrary.router) {
                            LibraryScreen()
                        }
                    }
                }
            }
        }
    }
}
