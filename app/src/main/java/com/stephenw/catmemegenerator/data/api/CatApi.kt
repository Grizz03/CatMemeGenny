package com.stephenw.catmemegenerator.data.api

import com.stephenw.catmemegenerator.data.model.Cats
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CatApi {

    @GET("cat")
    suspend fun getImage(
        @Query("filter") filter: String?,
        @Query("json") json: Boolean = true,
    ): Response<Cats>

    @GET("cat/gif")
    suspend fun getGif(
        @Query("filter") filter: String?,
        @Query("json") json: Boolean = true,
    ): Response<Cats>
}