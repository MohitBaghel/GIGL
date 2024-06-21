package com.system.gigl.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.system.gigl.models.Shorts
import com.system.gigl.models.Videos
import com.system.gigl.repo.VideoRepository
import com.system.gigl.utils.Result
import kotlinx.coroutines.launch

class MainViewModel(private  val repository: VideoRepository) : ViewModel() {

    private val _videos = MutableLiveData<Result<List<Videos>>>()
    val videos: LiveData<Result<List<Videos>>> = _videos

    private val _shorts = MutableLiveData<Result<List<Shorts>>>()
    val shorts: LiveData<Result<List<Shorts>>> = _shorts

    fun fetchVideos() {
        _videos.value = Result.Loading // Correct usage
        viewModelScope.launch {
            val result = repository.getVideos()
            _videos.postValue(result)
        }
    }

    fun fetchShorts() {
        _shorts.value = Result.Loading // Correct usage
        viewModelScope.launch {
            val result = repository.getShorts()
            _shorts.postValue(result)
        }
    }
}