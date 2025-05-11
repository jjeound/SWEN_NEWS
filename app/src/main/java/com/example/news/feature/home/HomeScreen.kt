package com.example.news.feature.home

import android.util.Log
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.example.news.R
import com.example.news.core.Dimens
import com.example.news.data.model.News
import com.example.news.feature.home.composable.NewsCard
import com.example.news.ui.theme.NewsTheme

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
    navigateToMore: (Boolean) -> Unit,
    navigateToDetail: (String) -> Unit,
    navigateToSetting: () -> Unit
) {
    val hotNewsUiState by homeViewModel.hotNewsUiState.collectAsStateWithLifecycle()
    val latestNewsUiState by homeViewModel.latestNewsUiState.collectAsStateWithLifecycle()

    val hotNewsList by homeViewModel.hotNewsList.collectAsStateWithLifecycle()
    val latestNewsList by homeViewModel.latestNewsList.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        HomeTopBar(
            navigateToSetting = navigateToSetting
        )
        HomeContent(
            hotNewsUiState = hotNewsUiState,
            latestNewsUiState = latestNewsUiState,
            hotNewsList = hotNewsList,
            latestNewsList = latestNewsList,
            fetchNextHotNews = homeViewModel::fetchNextHotNewsList,
            fetchNextLatestNews = homeViewModel::fetchNextLatestNewsList,
            navigateToMore = navigateToMore,
            navigateToDetail = navigateToDetail
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
    navigateToMore: (Boolean) -> Unit,
    navigateToDetail: (String) -> Unit,
){
    Column(
        modifier = Modifier.fillMaxSize().padding(
            horizontal = Dimens.horizontalPadding,
            vertical = Dimens.verticalPadding
        ),
        verticalArrangement = Arrangement.spacedBy(Dimens.gapLarge)
    ) {
        NewsList(
            uiState = hotNewsUiState,
            newsList = hotNewsList,
            fetchNextNews = fetchNextHotNews,
            navigateToMore = navigateToMore,
            navigateToDetail = navigateToDetail,
            isFirst = true
        )
        NewsList(
            uiState = latestNewsUiState,
            newsList = latestNewsList,
            fetchNextNews = fetchNextLatestNews,
            navigateToMore = navigateToMore,
            navigateToDetail = navigateToDetail,
            isFirst = false
        )
    }
}

@Composable
private fun NewsList(
    uiState: HomeUiState,
    newsList: List<News>,
    fetchNextNews: () -> Unit,
    navigateToMore: (Boolean) -> Unit,
    navigateToDetail: (String) -> Unit,
    isFirst : Boolean
){
    Box(modifier = Modifier.fillMaxSize()) {
        val threadHold = 8
        LazyColumn(
            modifier = Modifier.padding(Dimens.verticalPadding),
            verticalArrangement = Arrangement.spacedBy(Dimens.gapMedium),
        ) {
            stickyHeader {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = if(isFirst)"핫 뉴스 \uD83D\uDE80" else "최신 뉴스 \uD83D\uDCF0",
                        style = NewsTheme.typography.header,
                        color = NewsTheme.colors.textPrimary
                    )
                    Row(
                        modifier = Modifier.clickable{
                            navigateToMore(isFirst)
                        },
                    ) {
                        Text(
                            text = "전체 보기",
                            style = NewsTheme.typography.viewAll,
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
//                if ((index + threadHold) >= newsList.size && uiState != HomeUiState.Loading) {
//                    fetchNextNews()
//                }
                NewsCard(
                    news = news,
                    navigateToDetail = navigateToDetail,
                )
            }
            item {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        modifier = Modifier.clickable{
                            if(newsList.size >= threadHold && uiState != HomeUiState.Loading) {
                                fetchNextNews()
                            }
                        },
                        text = "더보기",
                        style = NewsTheme.typography.more,
                    )
                }
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