package com.example.news.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.news.feature.detail.DetailScreen
import com.example.news.feature.home.HomeScreen

@Composable
fun Navigation(navHostController: NavHostController){
    NavHost(
        navController = navHostController,
        startDestination = Screen.Home,
    ) {
        composable<Screen.Home>{
            HomeScreen()
        }
        composable<Screen.Detail>{
            DetailScreen()
        }
    }
}