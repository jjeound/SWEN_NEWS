package com.example.news.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.news.feature.detail.DetailScreen
import com.example.news.feature.home.HomeScreen
import com.example.news.feature.more.MoreScreen
import com.example.news.feature.setting.SettingDetailScreen
import com.example.news.feature.setting.SettingScreen

context(SharedTransitionScope)
@OptIn(ExperimentalSharedTransitionApi::class)
fun NavGraphBuilder.newsNavigation() {
    composable<Screen.Home> {
        HomeScreen()
    }

    composable<Screen.More>{
        val args = it.toRoute<Screen.More>()
        MoreScreen(
            isHot = args.isHot
        )
    }
    composable<Screen.Detail>{
        DetailScreen()
    }
    composable<Screen.Setting>{
        SettingScreen()
    }
    composable<Screen.SettingDetail> {
        val args = it.toRoute<Screen.SettingDetail>()
        SettingDetailScreen(args.order)
    }
}