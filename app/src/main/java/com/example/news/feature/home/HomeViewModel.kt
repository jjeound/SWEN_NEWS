package com.example.news.feature.home

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.data.model.News
import com.example.news.data.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository
): ViewModel() {

    internal val hotNewsUiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    internal val latestNewsUiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)

    private val hotNewsFetchingIndex: MutableStateFlow<Int> = MutableStateFlow(0)
    private val latestNewsFetchingIndex: MutableStateFlow<Int> = MutableStateFlow(0)

    @OptIn(ExperimentalCoroutinesApi::class)
    val hotNewsList: StateFlow<List<News>> = hotNewsFetchingIndex.flatMapLatest { page ->
        repository.fetchHotNews(
            page = page,
            onStart = { hotNewsUiState.tryEmit(HomeUiState.Loading) },
            onComplete = { hotNewsUiState.tryEmit(HomeUiState.Idle) },
            onError = { hotNewsUiState.tryEmit(HomeUiState.Error(it)) }
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList(),
    )

    @OptIn(ExperimentalCoroutinesApi::class)
    val latestNewsList: StateFlow<List<News>> = latestNewsFetchingIndex.flatMapLatest { page ->
        repository.fetchLatestNews(
            page = page,
            onStart = { latestNewsUiState.tryEmit(HomeUiState.Loading) },
            onComplete = { latestNewsUiState.tryEmit(HomeUiState.Idle) },
            onError = { latestNewsUiState.tryEmit(HomeUiState.Error(it)) }
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList(),
    )

    fun fetchNextHotNewsList() {
        if (hotNewsUiState.value != HomeUiState.Loading) {
            hotNewsFetchingIndex.value++
        }
    }

    fun fetchNextLatestNewsList() {
        if (latestNewsUiState.value != HomeUiState.Loading) {
            latestNewsFetchingIndex.value++
        }
    }
}

@Stable
internal sealed interface HomeUiState {

    data object Idle : HomeUiState

    data object Loading : HomeUiState

    data class Error(val message: String?) : HomeUiState
}