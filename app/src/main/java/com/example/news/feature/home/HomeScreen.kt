package com.example.news.feature.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.example.news.R
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
private fun NewsList(
    uiState: HomeUiState,
    newsList: List<News>,
    fetchNextNews: () -> Unit,
){
    Box(modifier = Modifier.fillMaxSize()) {
        val threadHold = 8
        LazyColumn(
            modifier = Modifier.padding(Dimens.verticalPadding),
            verticalArrangement = Arrangement.spacedBy(Dimens.gapMedium),
            state = rememberLazyListState(),
        ) {
            stickyHeader {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = "핫 뉴스 \uD83D\uDE80",
                        style = NewsTheme.typography.header,
                        color = NewsTheme.colors.textPrimary
                    )
                    Row(
                        modifier = Modifier.clickable{},
                    ) {
                        Text(
                            text = "더보기",
                            style = NewsTheme.typography.more,
                            color = NewsTheme.colors.textThird,
                        )
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.chevron_right_small),
                            contentDescription = null,
                            tint = NewsTheme.colors.textThird,
                        )
                    }
                }
            }
            itemsIndexed(items = newsList, key = { _, news -> news.id }) { index, news ->
                if ((index + threadHold) >= newsList.size && uiState != HomeUiState.Loading) {
                    fetchNextNews()
                }
                NewsCard(
                    news = news
                )
            }
        }

        if (uiState == HomeUiState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(Dimens.gapLarge),
                strokeWidth = Dimens.border
            )
        }
    }
}

@Composable
private fun NewsCard(
    news: News
){
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {

    }
}