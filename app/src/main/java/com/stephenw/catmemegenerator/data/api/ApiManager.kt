package com.stephenw.catmemegenerator.data.api

import com.stephenw.catmemegenerator.data.model.Cats
import retrofit2.Response
import javax.inject.Inject

class ApiManager @Inject constructor(
    private val service: CatApi
) {

    suspend fun getCatImage(filter: String?): Response<Cats> {
        return service.getImage(filter)
    }

    suspend fun getCatGif(filter: String?): Response<Cats> {
        return service.getGif(filter)
    }

}