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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.example.news.R
import com.example.news.core.Dimens
import com.example.news.navigation.currentComposeNavigator
import com.example.news.ui.theme.NewsTheme

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.ATAScreen(){
    val composeNavigator = currentComposeNavigator
    val instructions = listOf(
        "SWEN News는 데이터 사이언스와 AI 기술을 활용하여 한국 언론 생태계의 편향성을 정량적으로 분석하는 차세대 미디어 분석 플랫폼입니다.",
        "자연어 처리(NLP) 엔진이 실시간으로 한국 주요 언론사의 뉴스 기사를 수집하고 분석하여, 각 매체의 정치적 성향과 편향도를 객관적 수치로 환산합니다.",
        "딥러닝 기반 감정 분석 시스템이 기사의 감정적 톤과 어조를 실시간으로 평가하여 좌편향, 중도, 우편향 성향을 95% 이상의 정확도로 분류하며, 시계열 분석을 통해 언론사별 편향 트렌드의 변화 패턴을 추적합니다."
    )
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = "About this app",
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
            instructions.forEach {
                Text(
                    text = it,
                    style = NewsTheme.typography.body,
                    color = NewsTheme.colors.textPrimary,
                )
            }
        }
    }
}