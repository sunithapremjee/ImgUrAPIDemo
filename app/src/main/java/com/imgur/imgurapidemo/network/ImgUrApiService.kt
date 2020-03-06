package com.imgur.imgurapidemo.network;


import com.imgur.imgurapidemo.domain.ImageDetails
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import timber.log.Timber


private const val BASE_URL = "https://api.imgur.com/3/"

val clientId = "be10dde89a99596"
var okHttpClient = OkHttpClient.Builder()
    .addInterceptor { chain ->
        var request = chain.request()
        request =
            request.newBuilder().addHeader("Authorization", "Client-ID $clientId").build()
        chain.proceed(request)
    }
    .build()

/**
 * Build the Moshi object that Retrofit will be using, making sure to add the Kotlin adapter for
 * full Kotlin compatibility.
 */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())

    .build()

/**
 * Use the Retrofit builder to build a retrofit object using a Moshi converter with our Moshi
 * object.
 */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .client(okHttpClient)
    .build()

/**
 * A public interface that exposes the [getImagedetails] method
 */
interface ImgUrAPIService{
    /**
     * Returns a Coroutine [Deferred] [List] of [NetworkImageDetailsContainer] which can be fetched with await() if
     * in a Coroutine scope.
     */
    @GET("gallery/r/funny/{page}")
    fun getImagedetails(@Path("page") page:Int):

    // The Coroutine Call Adapter allows us to return a Deferred, a Job with a result
            Deferred<NetworkImageDetailsContainer>

}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object ImagesAPI {
    val retrofitService : ImgUrAPIService by lazy {
        Timber.d("ImagesAPI:retrofitService")
        retrofit.create(ImgUrAPIService::class.java) }
}



