package com.stephenw.catmemegenerator.data.repo


import com.stephenw.catmemegenerator.Utils.Resource
import com.stephenw.catmemegenerator.data.api.ApiManager
import com.stephenw.catmemegenerator.data.model.Cats
import javax.inject.Inject

class CatRepository @Inject constructor(
    private val apiManager: ApiManager
) {

    suspend fun getCatImage(
        filter: String?
    ): Resource<Cats> {
        return try {
            val response = apiManager.getCatImage(filter)
            if (response.isSuccessful && response.body() != null) {
                Resource.Success(response.body()!!)
            } else {
                Resource.Error(null, "No cat found!")
            }
        } catch (ex: Exception) {
            Resource.Error(ex, "Unexpected error")
        }
    }

    suspend fun getCatGif(
        filter: String?
    ): Resource<Cats> {
        return try {
            val response = apiManager.getCatGif(filter)
            if (response.isSuccessful && response.body() != null) {
                Resource.Success(response.body()!!)
            } else {
                Resource.Error(null, errorMsg = "No cat found")
            }
        } catch (ex: Exception) {
            Resource.Error(ex, "Unexpected error")
        }
    }

}