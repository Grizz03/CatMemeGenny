package com.stephenw.catmemegenerator.data.api

import com.stephenw.catmemegenerator.data.model.Cats
import retrofit2.Response
import javax.inject.Inject

class ApiManager @Inject constructor(
    private val service: CatApi
) {

    suspend fun getImage(filter: String?): Response<Cats> {
        return service.getCatImage(filter)
    }

    suspend fun getGif(filter: String?): Response<Cats> {
        return service.getCatGif(filter)
    }

}