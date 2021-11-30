package yuri.dyachenko.translation.di

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import yuri.dyachenko.translation.api.SkyEngApi
import yuri.dyachenko.translation.impl.RetrofitDataProviderImpl
import yuri.dyachenko.translation.model.DataProvider
import javax.inject.Singleton

@Module
class RetrofitModule {

    private val baseUrl = "https://dictionary.skyeng.ru/api/public/v1/"

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(
                OkHttpClient
                    .Builder()
                    .addInterceptor(HttpLoggingInterceptor()
                        .apply {
                            level = HttpLoggingInterceptor.Level.BODY
                        })
                    .build()
            )
            .addCallAdapterFactory(
                RxJava2CallAdapterFactory
                    .create()
            )
            .addConverterFactory(
                GsonConverterFactory
                    .create(
                        GsonBuilder()
                            .create()
                    )
            )
            .build()

    @Provides
    @Singleton
    fun provideGitHubApi(retrofit: Retrofit): SkyEngApi =
        retrofit.create(SkyEngApi::class.java)

    @Provides
    @Singleton
    fun provideDataProvider(skyEngApi: SkyEngApi): DataProvider =
        RetrofitDataProviderImpl(skyEngApi)
}