package com.example.news.feature.detail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Badge
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.news.R
import com.example.news.ui.theme.NewsTheme
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.news.core.Dimens
import com.example.news.data.model.Keyword
import com.example.news.data.model.NewsDetail
import com.example.news.data.model.Source

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    viewModel: DetailViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val newsDetail by viewModel.newsDetail.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())
    ) {
        TopAppBar(
            title = {},
            navigationIcon = {
                IconButton(
                    onClick = {}
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.chevron_left),
                        contentDescription = "back",
                        tint = NewsTheme.colors.iconDefault,
                    )
                }
            },
        )
        if (uiState == DetailsUiState.Idle && newsDetail != null) {
            DetailContent(newsDetail = newsDetail!!)
        } else {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    modifier = Modifier.wrapContentSize(),
                    color = NewsTheme.colors.iconDefault
                )
            }
        }
    }
}

@Composable
private fun DetailContent(
    newsDetail: NewsDetail,
) {
    var selectedIndex = 0
    Column(
        modifier = Modifier.fillMaxSize().padding(
            horizontal = Dimens.horizontalPadding,
            vertical = Dimens.verticalPadding
        ),
        verticalArrangement = Arrangement.spacedBy(Dimens.gapMedium)
    ) {
        Text(
            text = newsDetail.title,
            style = NewsTheme.typography.headlineLarge,
            color = NewsTheme.colors.textPrimary
        )
        AsyncImage(
            model = newsDetail.thumbnail,
            contentDescription = newsDetail.title,
            alignment = Alignment.Center,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth()
        )
        when(selectedIndex){
            0 -> {
                Summary(
                    summary = newsDetail.left.summary,
                    onLeftClick = {},
                    onCenterClick = {
                        selectedIndex = 1
                    },
                    onRightClick = {
                        selectedIndex = 2
                    }
                )
                KeywordAnalysis(
                    keyword = newsDetail.left.keywords
                )
                MediaList(
                    media = newsDetail.left.media
                )
            }
            1 -> {
                Summary(
                    summary = newsDetail.center.summary,
                    onLeftClick = {
                        selectedIndex = 0
                    },
                    onCenterClick = {},
                    onRightClick = {
                        selectedIndex = 2
                    }
                )
                KeywordAnalysis(
                    keyword = newsDetail.center.keywords
                )
                MediaList(
                    media = newsDetail.center.media
                )
            }
            2 -> {
                Summary(
                    summary = newsDetail.right.summary,
                    onLeftClick = {
                        selectedIndex = 0
                    },
                    onCenterClick = {
                        selectedIndex = 1
                    },
                    onRightClick = {}
                )
                KeywordAnalysis(
                    keyword = newsDetail.right.keywords
                )
                MediaList(
                    media = newsDetail.right.media
                )
            }
        }
        MediaOpList(
            mediaOp = newsDetail.mediaOp
        )
        OriginalSource(
            source = newsDetail.originalSource
        )
    }
}

@Composable
private fun Summary(
    summary: List<String>,
    onLeftClick: () -> Unit,
    onCenterClick: () -> Unit,
    onRightClick: () -> Unit,
){
    Column(
        modifier = Modifier.fillMaxWidth().padding(Dimens.gapMedium),
        verticalArrangement = Arrangement.spacedBy(Dimens.gapLarge)
    ) {
        Text(
            text = "요약",
            style = NewsTheme.typography.header,
            color = NewsTheme.colors.textPrimary
        )
        // Percent

        summary.forEach { text ->
            Text(
                text = text,
                style = NewsTheme.typography.body,
                color = NewsTheme.colors.textPrimary
            )
        }
    }
}

@Composable
private fun KeywordAnalysis(
    keyword: List<Keyword>,
){
    Column(
        modifier = Modifier.fillMaxWidth().padding(Dimens.gapMedium),
        verticalArrangement = Arrangement.spacedBy(Dimens.gapLarge)
    ) {
        Text(
            text = "키워드 분석",
            style = NewsTheme.typography.header,
            color = NewsTheme.colors.textPrimary
        )
        keyword.forEach { keyword ->
            Row(
                modifier = Modifier.fillMaxWidth().padding(Dimens.gapMedium),
                horizontalArrangement = Arrangement.spacedBy(Dimens.gapMedium)
            ) {
                Card(
                    modifier = Modifier
                        .size(keyword.count.dp)
                        .clip(RoundedCornerShape(Dimens.cornerRadius)),
                    shape = RoundedCornerShape(Dimens.cornerRadius),
                    border = BorderStroke(
                        width = Dimens.border,
                        color = NewsTheme.colors.optionBorderFocused
                    ),
                ) {
                    Text(
                        text = keyword.keyword,
                        style = NewsTheme.typography.keywordMedium,
                        color = NewsTheme.colors.textPrimary
                    )
                }
            }
        }
    }
}

@Composable
private fun MediaList(
    media: List<String>,
){
    Column(
        modifier = Modifier.fillMaxWidth().padding(Dimens.gapMedium),
        verticalArrangement = Arrangement.spacedBy(Dimens.gapLarge)
    ) {
        Text(
            text = "언론사",
            style = NewsTheme.typography.header,
            color = NewsTheme.colors.textPrimary
        )
        Row(
            modifier = Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()).padding(Dimens.gapMedium)
                .clip(shape = RoundedCornerShape(Dimens.cornerRadius)).background(
                    color = NewsTheme.colors.blueBackground
                ).shadow(elevation = Dimens.shadow),
            horizontalArrangement = Arrangement.spacedBy(Dimens.gapMedium)
        ) {
            media.forEach {
                AsyncImage(
                    model = it,
                    contentDescription = "media",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(48.dp)
                        .clip(RoundedCornerShape(Dimens.circle))
                        .shadow(
                            elevation = Dimens.shadow,
                            shape = RoundedCornerShape(Dimens.circle)
                        )
                )
            }
        }
    }
}

@Composable
private fun MediaOpList(
    mediaOp: List<String>,
){
    Column(
        modifier = Modifier.fillMaxWidth().padding(Dimens.gapMedium),
        verticalArrangement = Arrangement.spacedBy(Dimens.gapLarge)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "이 사건을 다루지 않은 언론사",
                style = NewsTheme.typography.header,
                color = NewsTheme.colors.textPrimary
            )
            Text(
                text = "2025.04.30 13:29분 기준",
                style = NewsTheme.typography.time,
                color = NewsTheme.colors.textThird
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()).padding(Dimens.gapMedium)
                .clip(shape = RoundedCornerShape(Dimens.cornerRadius)).background(
                    color = NewsTheme.colors.blueBackground
                ).shadow(
                    elevation = Dimens.shadow,
                ),
            horizontalArrangement = Arrangement.spacedBy(Dimens.gapMedium)
        ) {
            mediaOp.forEach {
                AsyncImage(
                    model = it,
                    contentDescription = "media",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(48.dp)
                        .clip(RoundedCornerShape(Dimens.circle))
                        .shadow(
                            elevation = Dimens.shadow,
                            shape = RoundedCornerShape(Dimens.circle)
                        )
                )
            }
        }
    }
}

@Composable
private fun OriginalSource(
    source: List<Source>
){
    var selectedIndex = 0
    val options = listOf("전체", "좌", "중도", "우")
    Column(
        modifier = Modifier.fillMaxWidth().padding(Dimens.gapMedium),
        verticalArrangement = Arrangement.spacedBy(Dimens.gapLarge)
    ) {
        Text(
            text = "원본 뉴스",
            style = NewsTheme.typography.header,
            color = NewsTheme.colors.textPrimary
        )
        Column(
            modifier = Modifier.fillMaxWidth()
        ){
            Column(
                modifier = Modifier.wrapContentSize()
            ) {
                options.forEachIndexed { index, option ->
                    Row(
                        modifier = Modifier.wrapContentWidth().clickable{
                            selectedIndex = index
                        },
                        horizontalArrangement = Arrangement.spacedBy(Dimens.gapSmall)
                    ) {
                        Text(
                            text = option,
                            style = NewsTheme.typography.body,
                            color = NewsTheme.colors.textPrimary
                        )
                        Badge(
                            modifier = Modifier.wrapContentSize(),
                            containerColor = NewsTheme.colors.badge,
                            content = {
                                Text(
                                    text = source.filter { it.bias == index }.size.toString(),
                                    style = NewsTheme.typography.badge,
                                    color = NewsTheme.colors.badgeText
                                )
                            }
                        )
                    }
                    if(selectedIndex == index){
                        HorizontalDivider(
                            modifier = Modifier.wrapContentWidth(),
                            color = NewsTheme.colors.optionBorderFocused
                        )
                    }
                }
            }
            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                color = NewsTheme.colors.optionBorderUnfocused
            )
        }
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(Dimens.gapLarge),
        ) {
            source.forEach { source ->
                item {
                    SourceCard(
                        source = source,
                        onClick = {}
                    )
                }
            }
        }
    }
}

@Composable
private fun SourceCard(
    source: Source,
    onClick: () -> Unit
){
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(Dimens.cornerRadius),
        border = BorderStroke(
            width = Dimens.border,
            color = NewsTheme.colors.buttonUnfocused
        ),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(Dimens.gapMedium),
            verticalArrangement = Arrangement.spacedBy(Dimens.gapSmall)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().clickable { onClick() },
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = source.media,
                    style = NewsTheme.typography.media,
                    color = NewsTheme.colors.textPrimary
                )
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.chevron_right),
                    contentDescription = "chevron right",
                    tint = NewsTheme.colors.iconDefault,
                )
            }
            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                color = NewsTheme.colors.divider
            )
            Text(
                text = source.title,
                style = NewsTheme.typography.description,
                color = NewsTheme.colors.textPrimary
            )
        }
    }
}