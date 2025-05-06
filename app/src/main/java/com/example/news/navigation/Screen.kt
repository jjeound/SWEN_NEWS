package com.example.news.navigation

import kotlinx.serialization.Serializable

sealed interface Screen{
    @Serializable
    data object Home: Screen
    @Serializable
    data class More(val isFirst: Boolean): Screen
    @Serializable
    data class Detail(val id: Long): Screen
    @Serializable
    data object Setting: Screen
}