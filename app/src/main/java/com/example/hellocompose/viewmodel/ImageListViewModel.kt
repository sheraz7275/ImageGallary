package com.example.hellocompose.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hellocompose.data.ImageItem
import com.example.hellocompose.repository.ImageRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ImageListViewModel : ViewModel() {
    private val repository = ImageRepository()
    private val _images = MutableStateFlow<List<ImageItem>>(emptyList())
    val images: StateFlow<List<ImageItem>> = _images

    init {
        fetchImages()
    }

    private fun fetchImages() {
        viewModelScope.launch {
            val result = repository.fetchImages()
            _images.value = result
        }
    }
}
