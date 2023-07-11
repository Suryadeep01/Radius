package com.example.radius.models

import com.squareup.moshi.Json

data class ResponseData(

    @Json(name = "facilities") var facilities : List<Facilities> = arrayListOf(),
    @Json(name = "exclusions") var exclusions : List<List<Exclusions>> = arrayListOf()

)