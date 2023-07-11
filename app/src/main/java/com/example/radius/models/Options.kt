package com.example.radius.models

import com.squareup.moshi.Json

data class Options (

    @Json(name = "name") var name : String? = null,
    @Json(name = "icon") var icon : String? = null,
    @Json(name = "id") var id   : String? = null

)