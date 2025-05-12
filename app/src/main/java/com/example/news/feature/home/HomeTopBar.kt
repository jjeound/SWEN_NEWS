package com.example.news.feature.home

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import com.example.news.R
import com.example.news.navigation.Screen
import com.example.news.navigation.currentComposeNavigator
import com.example.news.ui.theme.NewsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar() {
    val composeNavigator = currentComposeNavigator
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
                color = NewsTheme.colors.textPrimary,
                style = NewsTheme.typography.appBarTitle
            )
        },
        colors = TopAppBarDefaults.topAppBarColors().copy(
            containerColor = NewsTheme.colors.surface,
        ),
        actions = {
            IconButton(
                onClick = {
                    composeNavigator.navigate(Screen.Setting)
                },
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.setting),
                    contentDescription = "settings",
                    tint = NewsTheme.colors.iconDefault
                )
            }
        }
    )
}