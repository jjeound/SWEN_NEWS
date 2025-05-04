package com.example.news.feature.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import com.example.news.core.Dimens
import com.example.news.data.model.News
import com.example.news.ui.theme.NewsTheme

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
) {
    val hotNewsUiState by homeViewModel.hotNewsUiState.collectAsStateWithLifecycle()
    val latestNewsUiState by homeViewModel.latestNewsUiState.collectAsStateWithLifecycle()

    val hotNewsList by homeViewModel.hotNewsList.collectAsStateWithLifecycle()
    val latestNewsList by homeViewModel.latestNewsList.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        HomeTopBar()
        HomeContent(
            hotNewsUiState = hotNewsUiState,
            latestNewsUiState = latestNewsUiState,
            hotNewsList = hotNewsList,
            latestNewsList = latestNewsList,
            fetchNextHotNews = homeViewModel::fetchNextHotNewsList,
            fetchNextLatestNews = homeViewModel::fetchNextLatestNewsList,
        )
    }
}

@Composable
private fun HomeContent(
    hotNewsUiState: HomeUiState,
    latestNewsUiState: HomeUiState,
    hotNewsList: List<News>,
    latestNewsList: List<News>,
    fetchNextHotNews: () -> Unit,
    fetchNextLatestNews: () -> Unit,
){
    Column(
        modifier = Modifier.fillMaxSize().padding(
            horizontal = Dimens.horizontalPadding,
            vertical = Dimens.verticalPadding
        ),
        verticalArrangement = Arrangement.spacedBy(Dimens.gapLarge)
    ) {

    }
}



@Composable
private fun NewsCard(
    uiState: HomeUiState,
    news: List<News>,
    fetchNextNews: () -> Unit,
){
    Column {

    }
}