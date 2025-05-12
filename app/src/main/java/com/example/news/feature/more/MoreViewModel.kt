package com.example.news.feature.more

import androidx.compose.runtime.Stable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.data.model.News
import com.example.news.data.repository.MoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoreViewModel @Inject constructor(
    private val repository: MoreRepository,
    savedStateHandle: SavedStateHandle,
): ViewModel() {
    internal val uiState: MutableStateFlow<MoreUiState> = MutableStateFlow(MoreUiState.Loading)
    private val nextFetchingIndex: MutableStateFlow<Int> = MutableStateFlow(1)

    val bool = savedStateHandle.getStateFlow<Boolean?>("bool", true)

    var newsList: StateFlow<List<News>> = MutableStateFlow(emptyList())


    @OptIn(ExperimentalCoroutinesApi::class)
    fun fetchSingleCategoryNews(
        category: String,
    ) {
        nextFetchingIndex.update { 0 }
        viewModelScope.launch {
            if(bool.value == true){
                newsList = repository.fetchSingleCategoryHotNews (
                    category = category,
                    page = 0,
                    onStart = { uiState.tryEmit(MoreUiState.Loading) },
                    onComplete = { uiState.tryEmit(MoreUiState.Idle) },
                    onError = { uiState.tryEmit(MoreUiState.Error(it)) }
                ).stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5_000),
                    initialValue = emptyList(),
                )
            }else{
                newsList = repository.fetchSingleCategoryLatestNews (
                    category = category,
                    page = 0,
                    onStart = { uiState.tryEmit(MoreUiState.Loading) },
                    onComplete = { uiState.tryEmit(MoreUiState.Idle) },
                    onError = { uiState.tryEmit(MoreUiState.Error(it)) }
                ).stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5_000),
                    initialValue = emptyList(),
                )
            }
        }
    }

//    @OptIn(ExperimentalCoroutinesApi::class)
//    fun fetchSingleCategoryLatestNews(
//        category: String,
//    ) {
//        nextFetchingIndex.update { 0 }
//        viewModelScope.launch {
//            latestNewsList = repository.fetchSingleCategoryLatestNews (
//                category = category,
//                page = 0,
//                onStart = { uiState.tryEmit(MoreUiState.Loading) },
//                onComplete = { uiState.tryEmit(MoreUiState.Idle) },
//                onError = { uiState.tryEmit(MoreUiState.Error(it)) }
//            ).stateIn(
//                scope = viewModelScope,
//                started = SharingStarted.WhileSubscribed(5_000),
//                initialValue = emptyList(),
//            )
//        }
//    }

    fun fetchNextNewsList() {
        if (uiState.value != MoreUiState.Loading) {
            nextFetchingIndex.value++
        }
    }
}

@Stable
internal sealed interface MoreUiState {

    data object Idle : MoreUiState

    data object Loading : MoreUiState

    data class Error(val message: String?) : MoreUiState
}