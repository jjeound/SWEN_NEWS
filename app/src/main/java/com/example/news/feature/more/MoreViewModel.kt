package com.example.news.feature.more

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.map
import com.example.news.data.database.entity.mapper.asHotDomain
import com.example.news.data.database.entity.mapper.asLatestDomain
import com.example.news.data.model.News
import com.example.news.data.repository.MoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoreViewModel @Inject constructor(
    private val repository: MoreRepository,
    savedStateHandle: SavedStateHandle,
): ViewModel() {

    val bool = savedStateHandle.getStateFlow<Boolean?>("bool", null)
    val newsFetchingIndex: MutableStateFlow<Int> = MutableStateFlow(1)

    @OptIn(ExperimentalCoroutinesApi::class)
    var newsList: StateFlow<PagingData<News>> = newsFetchingIndex.flatMapLatest { page ->
        if(bool.value == true){
            repository.fetchHotNews().map { pagingData ->
                pagingData.map {
                    it.asHotDomain()
                }
            }
        }else{
            repository.fetchLatestNews().map { pagingData ->
                pagingData.map {
                    it.asLatestDomain()
                }
            }
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = PagingData.empty(),
    )


    @OptIn(ExperimentalCoroutinesApi::class)
    fun fetchSingleCategoryNews(
        category: String,
    ) {
        viewModelScope.launch {
            newsList = if(bool.value == true){
                repository.fetchHotNews(category).map { pagingData ->
                    pagingData.map {
                        it.asHotDomain()
                    }
                }
            }else{
                repository.fetchLatestNews(category).map { pagingData ->
                    pagingData.map {
                        it.asLatestDomain()
                    }
                }
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = PagingData.empty(),
            )
        }
    }
}