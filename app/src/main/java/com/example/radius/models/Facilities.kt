package com.example.radius.models

import com.squareup.moshi.Json

data class Facilities (

    @Json(name = "facility_id") var facilityId : String? = null,
    @Json(name = "name") var name : String? = null,
    @Json(name = "options") var options : List<Options> = arrayListOf()

)