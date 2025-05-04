package com.example.news

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.news.navigation.Navigation

@Composable
fun MainScreen(){
    val navHostController = rememberNavController()
    Navigation(navHostController)
}