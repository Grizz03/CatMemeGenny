package com.stephenw.catmemegenerator.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Cats(
    val url: String
)