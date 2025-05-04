package com.example.news.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.news.R

@Immutable
public data class Background(
    val color: Color = Color.Unspecified,
    val tonalElevation: Dp = Dp.Unspecified,
) {
    public companion object {
        @Composable
        public fun defaultBackground(darkTheme: Boolean): Background {
            return if (darkTheme) {
                Background(
                    color = colorResource(id = R.color.black_700),
                    tonalElevation = 0.dp,
                )
            } else {
                Background(
                    color = colorResource(id = R.color.white_700),
                    tonalElevation = 0.dp,
                )
            }
        }
    }
}

public val LocalBackgroundTheme: ProvidableCompositionLocal<Background> =
    staticCompositionLocalOf { Background() }