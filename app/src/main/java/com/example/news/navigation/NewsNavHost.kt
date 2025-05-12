package com.example.news.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun NewsNavHost(navHostController: NavHostController) {
    SharedTransitionLayout {
        NavHost(
            navController = navHostController,
            startDestination = Screen.Home,
        ) {
            newsNavigation()
        }
    }
}