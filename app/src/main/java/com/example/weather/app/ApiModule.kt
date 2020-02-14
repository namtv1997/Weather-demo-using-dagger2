package com.example.weather.app

import android.app.Application
import com.example.weather.utils.Constant
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Singleton
    fun provideOkHttpCache(application: Application): Cache {
        val size: Long = 100 * 1024 * 1024 // 100Mb
        return Cache(application.cacheDir, size)
    }

    @Provides
    @Singleton
    @Named("logger")
    fun provideLoggerInterceptor(): Interceptor {
        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BODY

        return logger
    }

    @Provides
    @Singleton
    @Named("auth_retrofit")
    fun provideRetrofit(@Named("auth_okhttp") okHttpClient: OkHttpClient): Retrofit {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.excludeFieldsWithoutExposeAnnotation()

        return Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
            .baseUrl(Constant.BASE_URL)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
     fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

}
