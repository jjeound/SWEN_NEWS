package com.example.news.feature.more

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.example.news.R
import com.example.news.navigation.currentComposeNavigator
import com.example.news.ui.theme.NewsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoreTopBar(
    isFirst: Boolean
) {
    val composeNavigator = currentComposeNavigator
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = if(isFirst)"핫 뉴스 \uD83D\uDE80" else "최신 뉴스 \uD83D\uDCF0",
                color = NewsTheme.colors.textPrimary,
                style = NewsTheme.typography.appBarTitle
            )
        },
        colors = TopAppBarDefaults.topAppBarColors().copy(
            containerColor = NewsTheme.colors.surface,
        ),
        navigationIcon = {
            IconButton(
                onClick = {
                    composeNavigator.navigateUp()
                }
            ){
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.chevron_left),
                    contentDescription = "back",
                    tint = NewsTheme.colors.iconDefault,
                )
            }
        }
    )
}