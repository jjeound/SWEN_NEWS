package com.example.news.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.example.news.R

@Immutable
public data class Colors(
    val surface: Color,
    val textPrimary: Color,
    val textSecondary: Color,
    val textThird: Color,
    val optionBorderUnfocused: Color,
    val optionTextUnfocused: Color,
    val buttonBorderUnfocused: Color,
    val buttonUnfocused: Color,
    val badge: Color,
    val badgeText: Color,
    val divider: Color,
    val center: Color,
    val optionBorderFocused: Color,
    val optionTextFocused: Color,
    val buttonTextUnfocused: Color,
    val iconSelected: Color,
    val iconDefault: Color,
    val blueBias: Color,
    val redBias: Color,
    val bluePercent: Color,
    val redPercent: Color,
    val blueBackground: Color,
    val redBackground: Color,
) {

    public companion object {
        @Composable
        public fun defaultDarkColors(): Colors = Colors(
            surface = colorResource(id = R.color.black_700),
            textPrimary = colorResource(id = R.color.white_700),
            textSecondary = colorResource(id = R.color.white_300),
            textThird = colorResource(id = R.color.white_100),
            optionBorderUnfocused = colorResource(id = R.color.black_400),
            optionTextUnfocused = colorResource(id = R.color.black_100),
            buttonBorderUnfocused = colorResource(id = R.color.black_300),
            buttonUnfocused = colorResource(id = R.color.black_400),
            badge = colorResource(id = R.color.white_300),
            badgeText = colorResource(id = R.color.black_700),
            divider = colorResource(id = R.color.black_600),
            center = colorResource(id = R.color.white_500),
            optionBorderFocused = colorResource(id = R.color.white_700),
            optionTextFocused = colorResource(id = R.color.white_700),
            buttonTextUnfocused = colorResource(id = R.color.white_100),
            iconSelected = colorResource(id = R.color.white_700),
            iconDefault = colorResource(id = R.color.black_200),
            blueBias = colorResource(id = R.color.blue_700),
            redBias = colorResource(id = R.color.red_700),
            bluePercent = colorResource(id = R.color.blue_100),
            redPercent = colorResource(id = R.color.red_100),
            blueBackground = colorResource(id = R.color.blue_100),
            redBackground = colorResource(id = R.color.red_100)
        )

        @Composable
        public fun defaultLightColors(): Colors = Colors(
            surface = colorResource(id = R.color.white_700),
            textPrimary = colorResource(id = R.color.black_700),
            textSecondary = colorResource(id = R.color.white_300),
            textThird = colorResource(id = R.color.white_100),
            optionBorderUnfocused = colorResource(id = R.color.black_400),
            optionTextUnfocused = colorResource(id = R.color.black_100),
            buttonBorderUnfocused = colorResource(id = R.color.black_300),
            buttonUnfocused = colorResource(id = R.color.black_400),
            badge = colorResource(id = R.color.white_300),
            badgeText = colorResource(id = R.color.black_700),
            divider = colorResource(id = R.color.black_600),
            center = colorResource(id = R.color.white_500),
            optionBorderFocused = colorResource(id = R.color.white_700),
            optionTextFocused = colorResource(id = R.color.white_700),
            buttonTextUnfocused = colorResource(id = R.color.white_100),
            iconSelected = colorResource(id = R.color.white_700),
            iconDefault = colorResource(id = R.color.black_200),
            blueBias = colorResource(id = R.color.blue_700),
            redBias = colorResource(id = R.color.red_700),
            bluePercent = colorResource(id = R.color.blue_100),
            redPercent = colorResource(id = R.color.red_100),
            blueBackground = colorResource(id = R.color.blue_100),
            redBackground = colorResource(id = R.color.red_100)
        )
    }
}