package com.example.news.feature.more

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.news.core.Dimens
import com.example.news.data.model.News
import com.example.news.feature.home.composable.NewsCard
import com.example.news.ui.theme.NewsTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.MoreScreen(
    viewModel: MoreViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val newsList by viewModel.newsList.collectAsStateWithLifecycle()
    val bool by viewModel.bool.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.fillMaxSize()
    ){
        MoreTopBar(
            isFirst = bool != false
        )
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = Dimens.border,
            color = NewsTheme.colors.divider
        )
        Column(
            modifier = Modifier.fillMaxSize().padding(
                horizontal = Dimens.horizontalPadding,
                vertical = Dimens.verticalPadding
            ),
            verticalArrangement = Arrangement.spacedBy(Dimens.gapMedium)
        ){
            CategoryTab(
                onTabSelected = { category ->
                    viewModel.fetchSingleCategoryNews(category)
                }
            )
            NewsList(
                uiState = uiState,
                newsList = newsList,
                fetchNextNews = viewModel::fetchNextNewsList,
            )
        }
    }
}

@Composable
fun CategoryTab(
    onTabSelected: (String) -> Unit
){
    var selectedIndex by remember { mutableIntStateOf(-1) }

    Row(
        modifier = Modifier.fillMaxWidth().padding(Dimens.verticalPadding),
        horizontalArrangement = Arrangement.spacedBy(Dimens.gapMedium)
    ) {
        Category.entries.forEachIndexed { index, category ->
            Card(
                modifier = Modifier
                    .wrapContentSize()
                    .clickable {
                        selectedIndex = index
                        onTabSelected(category.eng)
                    },
                colors = CardDefaults.cardColors(
                    containerColor = NewsTheme.colors.surface
                ),
                shape = RoundedCornerShape(Dimens.cornerRadius),
                border = BorderStroke(
                    width = Dimens.border,
                    color = if(selectedIndex == index) NewsTheme.colors.optionBorderFocused else NewsTheme.colors.optionBorderUnfocused
                )
            ) {
                Box(
                    modifier = Modifier.padding(
                        horizontal = Dimens.gapMedium,
                        vertical = Dimens.gapSmall
                    ),
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        text = category.kor,
                        style = NewsTheme.typography.option,
                        color = if(selectedIndex == index) NewsTheme.colors.optionTextFocused else NewsTheme.colors.optionTextUnfocused,
                    )
                }
            }
        }
    }
}

@Composable
private fun NewsList(
    uiState: MoreUiState,
    newsList: List<News>,
    fetchNextNews: () -> Unit,
){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        val threadHold = 0
        LazyColumn(
            modifier = Modifier.padding(Dimens.verticalPadding),
            verticalArrangement = Arrangement.spacedBy(Dimens.gapMedium),
        ) {
            itemsIndexed(items = newsList, key = { _, news -> news.id }) { index, news ->
                if ((index + threadHold) >= newsList.size && uiState != MoreUiState.Loading) {
                    fetchNextNews()
                }
                NewsCard(
                    news = news
                )
            }
        }

        if (uiState == MoreUiState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(Dimens.gapLarge),
                strokeWidth = Dimens.border
            )
        }
    }
}

enum class Category(
    val kor: String,
    val eng: String,
){
    POLITICS("정치", "politics"),
    ECONOMY("경제", "economy"),
//    SOCIETY("사회", "society"),
//    SPORTS("스포츠", "sports"),
//    ENTERTAINMENT("연예", "entertainment"),
//    LIFESTYLE("생활/문화", "lifestyle"),
//    IT_SCIENCE("IT과학", "it_science"),
//    WORLD("세계", "world"),
}