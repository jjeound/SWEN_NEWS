package com.example.news.feature.home

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.news.R
import com.example.news.core.Dimens
import com.example.news.data.model.News
import com.example.news.feature.home.composable.NewsCard
import com.example.news.navigation.Screen
import com.example.news.navigation.currentComposeNavigator
import com.example.news.ui.theme.NewsTheme

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val hotNewsUiState by homeViewModel.hotNewsUiState.collectAsStateWithLifecycle()
    val latestNewsUiState by homeViewModel.latestNewsUiState.collectAsStateWithLifecycle()
    val hotNewsList by homeViewModel.hotNewsList.collectAsStateWithLifecycle()
    val latestNewsList by homeViewModel.latestNewsList.collectAsStateWithLifecycle()
    val hotNewsIndex by homeViewModel.hotNewsFetchingIndex.collectAsStateWithLifecycle()
    val latestNewsIndex by homeViewModel.latestNewsFetchingIndex.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        HomeTopBar()
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = Dimens.border,
            color = NewsTheme.colors.divider
        )
        HomeContent(
            hotNewsUiState = hotNewsUiState,
            latestNewsUiState = latestNewsUiState,
            hotNewsList = hotNewsList,
            latestNewsList = latestNewsList,
            hotNewsIndex = hotNewsIndex,
            latestNewsIndex = latestNewsIndex,
            fetchNextHotNews = homeViewModel::fetchNextHotNewsList,
            fetchNextLatestNews = homeViewModel::fetchNextLatestNewsList,
            fetchPrevHotNews = homeViewModel::fetchPrevHotNewsList,
            fetchPrevLatestNews = homeViewModel::fetchPrevLatestNewsList,
            fetchThisHotNews = homeViewModel::fetchThisHotNewsList,
            fetchThisLatestNews = homeViewModel::fetchThisLatestNewsList,
        )
    }
}

@Composable
private fun HomeContent(
    hotNewsUiState: HomeUiState,
    latestNewsUiState: HomeUiState,
    hotNewsList: List<News>,
    latestNewsList: List<News>,
    hotNewsIndex: Int,
    latestNewsIndex: Int,
    fetchNextHotNews: () -> Unit,
    fetchNextLatestNews: () -> Unit,
    fetchPrevHotNews: () -> Unit,
    fetchPrevLatestNews: () -> Unit,
    fetchThisHotNews: (Int) -> Unit,
    fetchThisLatestNews: (Int) -> Unit,
){
    LazyColumn (
        modifier = Modifier
            .fillMaxSize()
            .padding(
                horizontal = Dimens.homeHorizontalPadding,
                vertical = Dimens.homeHorizontalPadding
            ),
        verticalArrangement = Arrangement.spacedBy(Dimens.gapMedium),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item {
            SectionHeader(
                header = "핫 뉴스 \uD83D\uDE80",
                bool = true,
            )
        }
        if(hotNewsUiState == HomeUiState.Loading){
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(40.dp),
                        color = NewsTheme.colors.center,
                    )
                }
            }
        } else {
            items(hotNewsList.size) {
                val news = hotNewsList[it]
                NewsCard(
                    news = news
                )
            }
            item {
                if(hotNewsList.isNotEmpty()){
                    NewsListFooter(
                        index = hotNewsIndex,
                        totalPages = hotNewsList[0].totalPages,
                        fetchPrev = fetchPrevHotNews,
                        fetchNext = fetchNextHotNews,
                        fetchThis = fetchThisHotNews,
                    )
                }
            }
        }
        item {
            SectionHeader(
                header = "최신 뉴스 \uD83D\uDCF0",
                bool = false,
            )
        }
        if(latestNewsUiState == HomeUiState.Loading){
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(40.dp),
                        color = NewsTheme.colors.center,
                    )
                }
            }
        } else {
            items(latestNewsList.size){
                val news = latestNewsList[it]
                NewsCard(
                    news = news
                )
            }
            item {
                if(latestNewsList.isNotEmpty()){
                    NewsListFooter(
                        index = latestNewsIndex,
                        totalPages = latestNewsList[0].totalPages,
                        fetchPrev = fetchPrevLatestNews,
                        fetchNext = fetchNextLatestNews,
                        fetchThis = fetchThisLatestNews,
                    )
                }
            }
        }
    }
}

@Composable
private fun SectionHeader(
    header: String,
    bool: Boolean,
){
    val composeNavigator = currentComposeNavigator
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = header,
            style = NewsTheme.typography.header,
            color = NewsTheme.colors.textPrimary
        )
        Row(
            modifier = Modifier.clickable{
                composeNavigator.navControllerFlow.value?.currentBackStackEntry?.savedStateHandle?.set("bool", bool)
                composeNavigator.navigate(Screen.More(bool))
            },
        ) {
            Text(
                text = "전체 보기",
                style = NewsTheme.typography.viewAll,
                color = NewsTheme.colors.textThird,
            )
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.chevron_right_small),
                contentDescription = "view all",
                tint = NewsTheme.colors.textThird,
            )
        }
    }
}

@Composable
private fun NewsListFooter(
    index: Int,
    totalPages: Int,
    fetchPrev: () -> Unit,
    fetchNext: () -> Unit,
    fetchThis: (Int) -> Unit,
){
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.wrapContentWidth(),
            horizontalArrangement = Arrangement.spacedBy(Dimens.gapMedium),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = fetchPrev,
                enabled = index > 1,
                colors = IconButtonDefaults.iconButtonColors(
                    disabledContentColor = NewsTheme.colors.iconDefault,
                    contentColor = NewsTheme.colors.iconSelected
                )
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.chevron_left_small),
                    contentDescription = "prev",
                )
            }
            if (index > 1) {
                Column {
                    Text(
                        modifier = Modifier
                            .padding(
                                horizontal = Dimens.gapSmall
                            )
                            .clickable {
                                fetchThis(index - 1)
                            },
                        text = "${index - 1}",
                        style = NewsTheme.typography.option,
                        color = NewsTheme.colors.optionTextUnfocused
                    )
                }
            }
            Column {
                Text(
                    modifier = Modifier.padding(
                        horizontal = Dimens.gapSmall
                    ),
                    text = "$index",
                    style = NewsTheme.typography.option,
                    color = NewsTheme.colors.optionTextFocused
                )
                HorizontalDivider(
                    modifier = Modifier.width(14.dp),
                    thickness = Dimens.border,
                    color = NewsTheme.colors.optionBorderFocused
                )
            }
            if (index + 1 <= totalPages) {
                Column {
                    Text(
                        modifier = Modifier
                            .padding(
                                horizontal = Dimens.gapSmall
                            )
                            .clickable {
                                fetchThis(index + 1)
                            },
                        text = "${index + 1}",
                        style = NewsTheme.typography.option,
                        color = NewsTheme.colors.optionTextUnfocused
                    )
                }

            }
            IconButton(
                onClick = fetchNext,
                enabled = index < totalPages,
                colors = IconButtonDefaults.iconButtonColors(
                    disabledContentColor = NewsTheme.colors.iconDefault,
                    contentColor = NewsTheme.colors.iconSelected
                )
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.chevron_right_small),
                    contentDescription = "prev",
                )
            }
        }
    }
}