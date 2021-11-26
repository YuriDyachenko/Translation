package yuri.dyachenko.translation.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object SkyEngApiFactory {

    private val okHttp =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()

    private val gson: Gson =
        GsonBuilder()
            .create()

    fun create(): SkyEngApi =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttp)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(SkyEngApi::class.java)

    private const val BASE_URL = "https://dictionary.skyeng.ru/api/public/v1/"
}