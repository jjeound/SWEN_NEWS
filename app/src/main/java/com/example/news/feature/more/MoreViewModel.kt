package com.example.news.feature.more

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
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class MoreViewModel @Inject constructor(
    private val repository: MoreRepository,
): ViewModel() {

    private val _newsList = MutableStateFlow<PagingData<News>>(PagingData.empty())
    val newsList: StateFlow<PagingData<News>> = _newsList

    fun fetchNewsList(
        isHot: Boolean,
        category: String? = null,
    ) {
        viewModelScope.launch {
            if (isHot){
                repository.fetchHotNews(category).map {
                    it.map { it.asHotDomain() }
                }
            } else {
                repository.fetchLatestNews(category).map {
                    it.map { it.asLatestDomain() }
                }
            }.stateIn(
                scope = viewModelScope,
                started = kotlinx.coroutines.flow.SharingStarted.WhileSubscribed(5_000),
                initialValue = PagingData.empty(),
            ).collectLatest {
                _newsList.value = it
            }
        }
    }
}