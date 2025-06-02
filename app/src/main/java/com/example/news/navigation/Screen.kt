package com.example.news.navigation

import kotlinx.serialization.Serializable

sealed interface Screen{
    @Serializable
    data object Home: Screen
    @Serializable
    data class More(val isHot: Boolean): Screen
    @Serializable
    data class Detail(val id: String): Screen
    @Serializable
    data object Setting: Screen
    @Serializable
    data class SettingDetail(val order: Int): Screen
}