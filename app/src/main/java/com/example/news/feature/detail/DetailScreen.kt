package com.example.news.feature.detail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.news.core.Dimens
import com.example.news.ui.theme.NewsTheme

@Composable
fun DetailScreen() {}



@Composable
fun CategoryTab(){
    val categories = listOf(
        "정치",
        "경제",
        "사회",
        "스포츠",
        "연예",
        "생활/문화",
        "IT과학",
        "세계",
    )
    var selectedIndex = 0

    Row(
        modifier = Modifier.fillMaxWidth().padding(Dimens.verticalPadding),
        horizontalArrangement = Arrangement.spacedBy(Dimens.gapMedium)
    ) {
        categories.forEachIndexed { index, category ->
            Card(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(Dimens.verticalPadding)
                    .clickable {
                        selectedIndex = index
                    },
                shape = RoundedCornerShape(Dimens.cornerRadius),
                border = BorderStroke(
                    width = Dimens.border,
                    color = if(selectedIndex == index) NewsTheme.colors.optionBorderFocused else NewsTheme.colors.optionBorderUnfocused
                )
            ) {
                Box(
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        text = category,
                        style = NewsTheme.typography.option,
                        color = if(selectedIndex == index) NewsTheme.colors.optionTextFocused else NewsTheme.colors.optionTextUnfocused,
                    )
                }
            }
        }
    }
}