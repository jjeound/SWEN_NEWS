package com.example.news.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.news.feature.detail.DetailScreen
import com.example.news.feature.home.HomeScreen
import com.example.news.feature.more.MoreScreen

@Composable
fun Navigation(navHostController: NavHostController){
    NavHost(
        navController = navHostController,
        startDestination = Screen.Home,
    ) {
        composable<Screen.Home>{
            HomeScreen(
                navigateToMore = { isFirst ->
                    navHostController.navigate(Screen.More(isFirst))
                },
            )
        }
        composable<Screen.More>{
            val args = it.toRoute<Screen.More>()
            MoreScreen(
                isFirst = args.isFirst,
                navigateToDetail = { newsId ->
                    navHostController.navigate(Screen.Detail(newsId))
                }
            )
        }
        composable<Screen.Detail>{
            DetailScreen()
        }
    }
}