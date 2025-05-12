package com.example.news.feature.home.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.news.R
import com.example.news.core.Dimens
import com.example.news.data.model.News
import com.example.news.navigation.Screen
import com.example.news.navigation.currentComposeNavigator
import com.example.news.ui.theme.NewsTheme

@Composable
fun NewsCard(
    news: News
){
    val composeNavigator = currentComposeNavigator
    Row(
        modifier = Modifier.fillMaxWidth().padding(
            vertical = Dimens.verticalPadding
        ).clickable{
            composeNavigator.navControllerFlow.value?.currentBackStackEntry?.savedStateHandle?.set("id", news.id)
            composeNavigator.navigate(Screen.Detail(news.id))
        },
        horizontalArrangement = Arrangement.spacedBy(Dimens.gapMedium),
    ) {
        if(news.thumbnail != null){
            AsyncImage(
                model = news.thumbnail,
                contentDescription = news.title,
                alignment = Alignment.Center,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(92.dp)
            )
        }else{
            Image(
                imageVector = ImageVector.vectorResource(R.drawable.ic_launcher_foreground),
                contentDescription = news.title,
                modifier = Modifier.size(92.dp)
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(Dimens.gapSmall)
        ) {
            Text(
                text = news.title,
                style = NewsTheme.typography.headlineSmall,
                color = NewsTheme.colors.textPrimary
            )
            Row(
                modifier = Modifier.padding(
                    vertical = Dimens.gapSmall
                ),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                HorizontalDivider(
                    modifier = Modifier.width(news.left.dp),
                    thickness = Dimens.gapSmall,
                    color = NewsTheme.colors.blueBias
                )
                HorizontalDivider(
                    modifier = Modifier.width(news.center.dp),
                    thickness = Dimens.gapSmall,
                    color = NewsTheme.colors.center
                )
                HorizontalDivider(
                    modifier = Modifier.width(news.right.dp),
                    thickness = Dimens.gapSmall,
                    color = NewsTheme.colors.redBias
                )
                Spacer(
                    modifier = Modifier.width(Dimens.gapSmall)
                )
                Text(
                    text = "좌 ${news.left}% 중도 ${news.center}% 우 ${news.right}%",
                    style = NewsTheme.typography.percent,
                    color = NewsTheme.colors.textSecondary
                )
            }
        }
    }
}