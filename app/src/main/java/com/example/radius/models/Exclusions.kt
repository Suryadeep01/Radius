package com.example.radius.models

import com.squareup.moshi.Json

data class Exclusions (

    @Json(name = "facility_id") var facilityId : String? = null,
    @Json(name = "options_id") var optionsId  : String? = null

)