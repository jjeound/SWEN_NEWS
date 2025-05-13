package com.example.news.feature.more

import android.util.Log
import androidx.compose.runtime.Stable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.core.Resource
import com.example.news.data.model.News
import com.example.news.data.repository.HomeRepository
import com.example.news.data.repository.MoreRepository
import com.example.news.feature.home.HomeUiState
import com.example.news.feature.home.HomeViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoreViewModel @Inject constructor(
    private val homeRepository: HomeRepository,
    private val moreRepository: MoreRepository,
    savedStateHandle: SavedStateHandle,
): ViewModel() {
    internal val uiState: MutableStateFlow<MoreUiState> = MutableStateFlow(MoreUiState.Loading)
    private val nextFetchingIndex: MutableStateFlow<Int> = MutableStateFlow(1)

    val bool = savedStateHandle.getStateFlow<Boolean?>("bool", true)

    @OptIn(ExperimentalCoroutinesApi::class)
    var newsList: StateFlow<List<News>> = nextFetchingIndex.flatMapLatest { page ->
        homeRepository.fetchHotNews(
            page = page,
            limit = PAGE_SIZE,
        ).map { resource ->
            when (resource) {
                is Resource.Success -> {
                    uiState.tryEmit(MoreUiState.Idle)
                    resource.data ?: emptyList()
                }
                is Resource.Error -> {
                    uiState.tryEmit(MoreUiState.Error(resource.message))
                    Log.d("TAG", "error: ${resource.message}")
                    emptyList()
                }
                is Resource.Loading -> {
                    uiState.tryEmit(MoreUiState.Loading)
                    emptyList()
                }
            }
        }
//        if (bool.value == true) {
//            homeRepository.fetchHotNews(
//                page = page,
//                limit = PAGE_SIZE,
//            ).map { resource ->
//                when (resource) {
//                    is Resource.Success -> {
//                        uiState.tryEmit(MoreUiState.Idle)
//                        resource.data ?: emptyList()
//                    }
//                    is Resource.Error -> {
//                        uiState.tryEmit(MoreUiState.Error(resource.message))
//                        emptyList()
//                    }
//                    is Resource.Loading -> {
//                        uiState.tryEmit(MoreUiState.Loading)
//                        emptyList()
//                    }
//                }
//            }
//        } else {
//            homeRepository.fetchLatestNews(
//                page = page,
//                limit = PAGE_SIZE,
//            ).map { resource ->
//                when (resource) {
//                    is Resource.Success -> {
//                        uiState.tryEmit(MoreUiState.Idle)
//                        resource.data ?: emptyList()
//                    }
//                    is Resource.Error -> {
//                        uiState.tryEmit(MoreUiState.Error(resource.message))
//                        emptyList()
//                    }
//                    is Resource.Loading -> {
//                        uiState.tryEmit(MoreUiState.Loading)
//                        emptyList()
//                    }
//                }
//            }
//        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList(),
    )



    @OptIn(ExperimentalCoroutinesApi::class)
    fun fetchSingleCategoryNews(
        category: String,
    ) {
        nextFetchingIndex.update { 0 }
        viewModelScope.launch {
            if(bool.value == true){
                newsList = moreRepository.fetchSingleCategoryHotNews (
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
                newsList = moreRepository.fetchSingleCategoryLatestNews (
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

    fun fetchNextNewsList() {
        if (uiState.value != MoreUiState.Loading) {
            nextFetchingIndex.value++
        }
    }
    companion object {
        const val PAGE_SIZE = 10
    }
}

@Stable
internal sealed interface MoreUiState {

    data object Idle : MoreUiState

    data object Loading : MoreUiState

    data class Error(val message: String?) : MoreUiState
}