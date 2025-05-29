package com.example.news.feature.setting

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.example.news.R
import com.example.news.core.Dimens
import com.example.news.navigation.Screen
import com.example.news.navigation.currentComposeNavigator
import com.example.news.ui.theme.NewsTheme

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.SettingScreen(){
    val composeNavigator = currentComposeNavigator
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = "설정",
                    color = NewsTheme.colors.textPrimary,
                    style = NewsTheme.typography.appBarTitle
                )
            },
            colors = TopAppBarDefaults.topAppBarColors().copy(
                containerColor = NewsTheme.colors.surface,
            ),
            navigationIcon = {
                IconButton(
                    onClick = {composeNavigator.navigateUp()},
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.chevron_left),
                        contentDescription = "settings",
                        tint = NewsTheme.colors.iconDefault
                    )
                }
            }
        )
        Column(
            modifier = Modifier.fillMaxWidth().padding(
                horizontal = Dimens.horizontalPadding,
                vertical = Dimens.verticalPadding
            ),
            verticalArrangement = Arrangement.spacedBy(Dimens.gapMedium)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(Dimens.gapMedium),
                verticalArrangement = Arrangement.spacedBy(Dimens.gapSmall)
            ) {
                Text(
                    text = "지원 및 도움",
                    style = NewsTheme.typography.header,
                    color = NewsTheme.colors.textPrimary
                )
                Row(
                    modifier = Modifier.fillMaxWidth().clickable{},
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Feedback",
                        style = NewsTheme.typography.menu,
                        color = NewsTheme.colors.textPrimary
                    )
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.chevron_right),
                            contentDescription = "chevron right",
                            tint = NewsTheme.colors.iconDefault
                        )
                    }
                }
            }
            Column(
                modifier = Modifier.fillMaxWidth().padding(Dimens.gapMedium),
                verticalArrangement = Arrangement.spacedBy(Dimens.gapSmall)
            ) {
                Text(
                    text = "약관 및 정책",
                    style = NewsTheme.typography.header,
                    color = NewsTheme.colors.textPrimary
                )
                Row(
                    modifier = Modifier.fillMaxWidth().clickable{},
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "About this app",
                        style = NewsTheme.typography.menu,
                        color = NewsTheme.colors.textPrimary
                    )
                    IconButton(
                        onClick = {
                            composeNavigator.navigate(Screen.ATA)
                        }
                    ){
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.chevron_right),
                            contentDescription = "chevron right",
                            tint = NewsTheme.colors.iconDefault
                        )
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth().clickable{},
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "FAQ",
                        style = NewsTheme.typography.menu,
                        color = NewsTheme.colors.textPrimary
                    )
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.chevron_right),
                            contentDescription = "chevron right",
                            tint = NewsTheme.colors.iconDefault
                        )
                    }
                }
            }
        }
    }
}