package com.example.news.feature.detail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.news.core.Dimens
import com.example.news.data.dto.BiasRatio
import com.example.news.data.dto.Center
import com.example.news.data.dto.Keyword
import com.example.news.data.dto.Left
import com.example.news.data.dto.NewsInfo
import com.example.news.data.dto.Right

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    viewModel: DetailViewModel = hiltViewModel(),
    navigateUp: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val newsInfo by viewModel.newsDetail.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())
    ) {
        TopAppBar(
            title = {},
            colors = TopAppBarDefaults.topAppBarColors().copy(
                containerColor = NewsTheme.colors.surface,
            ),
            navigationIcon = {
                IconButton(
                    onClick = {
                        navigateUp()
                    }
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.chevron_left),
                        contentDescription = "back",
                        tint = NewsTheme.colors.iconDefault,
                    )
                }
            },
        )
        if (uiState == DetailsUiState.Idle && newsInfo != null) {
            DetailContent(newsInfo = newsInfo!!)
        } else {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
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
    newsInfo: NewsInfo,
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
            text = newsInfo.title,
            style = NewsTheme.typography.headlineLarge,
            color = NewsTheme.colors.textPrimary
        )
//        AsyncImage(
//            model = newsInfo.thumbnail,
//            contentDescription = newsInfo.title,
//            alignment = Alignment.Center,
//            contentScale = ContentScale.Crop,
//            modifier = Modifier.fillMaxWidth()
//        )
        Image(
            imageVector = ImageVector.vectorResource(R.drawable.ic_launcher_foreground),
            contentDescription = newsInfo.title,
            modifier = Modifier.fillMaxWidth()
        )
        when(selectedIndex){
            0 -> {
                if (newsInfo.left != null){
                    Summary(
                        summary = newsInfo.left.summary,
                        counts = newsInfo.articleIds.size,
                        biasRatio = newsInfo.biasRatio,
                        selectedIndex = selectedIndex,
                        onClick = {
                            selectedIndex = it
                        }
                    )
                    KeywordAnalysis(
                        keyword = newsInfo.left.keywords
                    )
                    MediaList(
                        media = newsInfo.left.articleIds
                    )
                } else {
                    Summary(
                        counts = newsInfo.articleIds.size,
                        biasRatio = newsInfo.biasRatio,
                        selectedIndex = selectedIndex,
                        onClick = {
                            selectedIndex = it
                        }
                    )
                }
            }
            1 -> {
                if(newsInfo.center != null){
                    Summary(
                        summary = newsInfo.center.summary,
                        counts = newsInfo.articleIds.size,
                        biasRatio = newsInfo.biasRatio,
                        selectedIndex = selectedIndex,
                        onClick = {
                            selectedIndex = it
                        }
                    )
                    KeywordAnalysis(
                        keyword = newsInfo.center.keywords
                    )
                    MediaList(
                        media = newsInfo.center.articleIds
                    )
                }else {
                    Summary(
                        counts = newsInfo.articleIds.size,
                        biasRatio = newsInfo.biasRatio,
                        selectedIndex = selectedIndex,
                        onClick = {
                            selectedIndex = it
                        }
                    )
                }
            }
            2 -> {
                if(newsInfo.right != null){
                    Summary(
                        summary = newsInfo.right.summary,
                        counts = newsInfo.articleIds.size,
                        biasRatio = newsInfo.biasRatio,
                        selectedIndex = selectedIndex,
                        onClick = {
                            selectedIndex = it
                        }
                    )
                    KeywordAnalysis(
                        keyword = newsInfo.right.keywords
                    )
                    MediaList(
                        media = newsInfo.right.articleIds
                    )
                }else {
                    Summary(
                        counts = newsInfo.articleIds.size,
                        biasRatio = newsInfo.biasRatio,
                        selectedIndex = selectedIndex,
                        onClick = {
                            selectedIndex = it
                        }
                    )
                }
            }
        }
        MediaOpList(
            mediaOp = newsInfo.articleIds //수정
        )
        OriginalSource(
            sourceAll = newsInfo.articleUrls,
            sourceLeft = newsInfo.left,
            sourceCenter = newsInfo.center,
            sourceRight = newsInfo.right
        )
    }
}

@Composable
private fun Summary(
    summary: String? = null,
    counts: Int,
    biasRatio: BiasRatio,
    selectedIndex : Int,
    onClick: (Int) -> Unit,
){
    val options = listOf("좌", "중도", "우")
    val ratio = listOf(biasRatio.left, biasRatio.center, biasRatio.right)
    Column(
        modifier = Modifier.fillMaxWidth().padding(Dimens.gapMedium),
        verticalArrangement = Arrangement.spacedBy(Dimens.gapLarge)
    ) {
        Text(
            text = "요약",
            style = NewsTheme.typography.header,
            color = NewsTheme.colors.textPrimary
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                options.forEachIndexed { index, option ->
                    if(selectedIndex == index){
                        val bgColor = when(index){
                            0 -> NewsTheme.colors.blueBias
                            1 -> NewsTheme.colors.center
                            2 -> NewsTheme.colors.redBias
                            else -> NewsTheme.colors.buttonUnfocused
                        }
                        Box(
                            modifier = Modifier
                                .width(50.dp).height(90.dp)
                                .padding(
                                    horizontal = Dimens.horizontalPadding,
                                    vertical = Dimens.gapSmall
                                )
                                .clickable { onClick(index) }
                                .background(
                                    brush = Brush.verticalGradient(
                                        colors = listOf(bgColor, NewsTheme.colors.surface)
                                    )
                                )
                            ,
                            contentAlignment = Alignment.Center
                        ) {
                            Column(
                                verticalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = option,
                                    style = NewsTheme.typography.button,
                                    color = NewsTheme.colors.buttonTextUnfocused
                                )
                                Text(
                                    text = "${ratio[index]*100}%",
                                    style = NewsTheme.typography.button,
                                    color = NewsTheme.colors.buttonTextUnfocused
                                )
                            }
                        }
                    }else{
                        Box(
                            modifier = Modifier
                                .width(50.dp)
                                .padding(
                                    horizontal = Dimens.horizontalPadding,
                                    vertical = Dimens.gapSmall
                                )
                                .clickable { onClick(index) }
                                .background(NewsTheme.colors.buttonUnfocused),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = option,
                                style = NewsTheme.typography.button,
                                color = NewsTheme.colors.buttonTextUnfocused
                            )
                        }
                    }
                }
            }
            Text(
                text = "총 ${counts}개 기사",
                style = NewsTheme.typography.time,
                color = NewsTheme.colors.textThird
            )
        }
        if(summary != null){
            val text = summary.split(".")
            text.forEach { it ->
                Text(
                    text = it,
                    style = NewsTheme.typography.body,
                    color = NewsTheme.colors.textPrimary
                )
            }
        }else{
            Box(
                modifier = Modifier.fillMaxWidth().padding(
                    vertical = Dimens.gapHuge
                ),
                contentAlignment = Alignment.Center
            ){
                Text(
                    text = "데이터가 없습니다.",
                    style = NewsTheme.typography.body,
                    color = NewsTheme.colors.textPrimary
                )
            }
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
                        .size((keyword.score*100).toInt().dp)
                        .clip(RoundedCornerShape(Dimens.cornerRadius)),
                    shape = RoundedCornerShape(Dimens.cornerRadius),
                    border = BorderStroke(
                        width = Dimens.border,
                        color = NewsTheme.colors.optionBorderFocused
                    ),
                ) {
                    Text(
                        text = keyword.word,
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
    sourceAll: List<String>,
    sourceLeft: Left?,
    sourceCenter: Center?,
    sourceRight: Right?,
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
                        val counts = when(selectedIndex){
                            0 -> (sourceLeft?.articleUrls?.size ?: 0) + (sourceCenter?.articleUrls?.size ?: 0) + (sourceRight?.articleUrls?.size ?: 0)
                            1 -> sourceLeft?.articleUrls?.size ?: 0
                            2 -> sourceCenter?.articleUrls?.size ?: 0
                            3 -> sourceRight?.articleUrls?.size ?: 0
                            else -> 0
                        }
                        Badge(
                            modifier = Modifier.wrapContentSize(),
                            containerColor = NewsTheme.colors.badge,
                            content = {
                                Text(
                                    text = counts.toString(),
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
            when(selectedIndex){
                0 -> {
                    sourceAll.forEach { url ->
                        item {
                            SourceCard(
                                onClick = {

                                }
                            )
                        }
                    }
                }
                1 -> {
                    sourceLeft?.let {
                        it.articleUrls.forEach { url ->
                            item {
                                SourceCard(
                                    onClick = {}
                                )
                            }
                        }
                    }
                }
                2 -> {
                    sourceCenter?.let {
                        it.articleUrls.forEach { url ->
                            item {
                                SourceCard(
                                    onClick = {}
                                )
                            }
                        }
                    }
                }
                3 -> {
                    sourceRight?.let {
                        it.articleUrls.forEach { url ->
                            item {
                                SourceCard(
                                    onClick = {}
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun SourceCard(
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
                    text = "언론사 이름",
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
                text = "뉴스 제목",
                style = NewsTheme.typography.description,
                color = NewsTheme.colors.textPrimary
            )
        }
    }
}