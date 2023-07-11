package com.example.radius.network

import com.example.radius.models.ResponseData
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://my-json-server.typicode.com/"

/**
 * Build the Moshi object that Retrofit will be using, making sure to add the Kotlin adapter for
 * full Kotlin compatibility.
 */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

//creating a Network Interceptor to add api_key in all the request as authInterceptor
private val interceptor = Interceptor { chain ->
    val request = chain.request()
        .newBuilder()
        .build()
    chain.proceed(request)
}
// we are creating a networking client using OkHttp and add our authInterceptor.
private val apiClient = OkHttpClient().newBuilder().addInterceptor(interceptor).build()

/**
 * Use the Retrofit builder to build a retrofit object using a Moshi converter with our Moshi
 * object.
 */
private val retrofit = Retrofit.Builder()
    .client(apiClient)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()


interface RadiusApiService {

    @GET("iranjith4/ad-assignment/db")
    suspend fun getFacilitiesData(): ResponseData
}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object RadiusApi {
    val retrofitService: RadiusApiService by lazy { retrofit.create(RadiusApiService::class.java) }
}
