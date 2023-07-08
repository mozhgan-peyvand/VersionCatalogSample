package com.example.base

sealed class Screen (val router: String) {
    object SampleLibrary : Screen("SampleLibrary")
}