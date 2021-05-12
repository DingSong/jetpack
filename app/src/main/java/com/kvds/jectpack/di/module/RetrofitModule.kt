package com.kvds.jectpack.di.module

import com.kvds.jectpack.BuildConfig
import com.kvds.jectpack.api.ApiService
import com.kvds.jectpack.common.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttp: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL).client(okHttp)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(httpLoggingInterceptor).build()
        }
        return builder.build()
    }
}