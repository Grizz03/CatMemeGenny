package com.stephenw.catmemegenerator.ui

import android.util.Log
import androidx.lifecycle.*
import com.bumptech.glide.load.engine.Resource
import com.stephenw.catmemegenerator.data.model.Cats
import com.stephenw.catmemegenerator.data.repo.CatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: CatRepository,
    private val state: SavedStateHandle
) : ViewModel() {

    private var _catImages = MutableLiveData<Resource<Cats>>()
    val catImages: LiveData<Resource<Cats>> get() = _catImages

    var imageType = state.get<ImageType>("image_type") ?: ImageType.IMAGE
        set(value) {
            field = value
            state.set("image_type", value)
        }

    var filter = state.get<String>("x")
        set(value) {
            field = value
            state.set("x", value)
        }

    fun getImage() {
        viewModelScope.launch(Dispatchers.IO) {
            when (imageType) {
                ImageType.IMAGE -> {
                    _catImages.postValue(repository.getCatImage(filter))
                }
                ImageType.GIF -> {
                    val response = repository.getCatGif(filter)
                    _catImages.postValue(response)
                }
            }
        }
    }


    enum class ImageType {
        IMAGE, GIF
    }
}

