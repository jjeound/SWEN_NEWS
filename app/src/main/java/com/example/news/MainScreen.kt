package com.example.news

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.rememberNavController
import com.example.news.navigation.AppComposeNavigator
import com.example.news.navigation.NewsNavHost
import com.example.news.navigation.Screen
import com.example.news.ui.theme.NewsTheme

@Composable
fun MainScreen(composeNavigator: AppComposeNavigator<Screen>) {
    NewsTheme {
        val navHostController = rememberNavController()

        LaunchedEffect(Unit) {
            composeNavigator.handleNavigationCommands(navHostController)
        }

        NewsNavHost(navHostController = navHostController)
    }
}