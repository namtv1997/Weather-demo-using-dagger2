package com.example.weather.data.source.remote


import android.content.Context
import com.example.weather.data.repository.WeatherRepositoryImp
import com.example.weather.domain.repository.WeatherRepository
import com.example.weather.utils.Constant
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class ApiModule {
    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.excludeFieldsWithoutExposeAnnotation()
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val gson = GsonBuilder().serializeNulls().setPrettyPrinting().create()
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
        val retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(Constant.BASE_URL)
            .client(client)
            .build()
        return retrofit.create(ApiService::class.java)
    }

//    @Provides
//    @Singleton
//    fun providesRetrofit(
//        gsonConverterFactory: GsonConverterFactory,
//        rxJava2CallAdapterFactory: RxJava2CallAdapterFactory,
//        okHttpClient: OkHttpClient
//    ): Retrofit {
//        return Retrofit.Builder()
//            .baseUrl(Constant.BASE_URL)
//            .addConverterFactory(gsonConverterFactory)
//            .addCallAdapterFactory(rxJava2CallAdapterFactory)
//            .client(okHttpClient)
//            .build()
//    }

//    @Provides
//    @Singleton
//    fun providesOkHttpClient(context: Context, isNetworkAvailable:Boolean): OkHttpClient {
//        val cacheSize = (5 * 1024 * 1024).toLong()
//        val mCache = Cache(context.cacheDir, cacheSize)
//        val interceptor = HttpLoggingInterceptor()
//        interceptor.level = HttpLoggingInterceptor.Level.BODY
//        val client = OkHttpClient.Builder()
//            .cache(mCache) // make your app offline-friendly without a database!
//            .connectTimeout(60, TimeUnit.SECONDS)
//            .writeTimeout(60, TimeUnit.SECONDS)
//            .readTimeout(60, TimeUnit.SECONDS)
//            .addNetworkInterceptor(interceptor)
//            .addInterceptor { chain ->
//                var request = chain.request()
//                /* If there is Internet, get the cache that was stored 5 seconds ago.
//                 * If the cache is older than 5 seconds, then discard it,
//                 * and indicate an error in fetching the response.
//                 * The 'max-age' attribute is responsible for this behavior.
//                 */
//                request = if (isNetworkAvailable) request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
//                /*If there is no Internet, get the cache that was stored 7 days ago.
//                 * If the cache is older than 7 days, then discard it,
//                 * and indicate an error in fetching the response.
//                 * The 'max-stale' attribute is responsible for this behavior.
//                 * The 'only-if-cached' attribute indicates to not retrieve new data; fetch the cache only instead.
//                 */
//                else request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build()
//                chain.proceed(request)
//            }
//        return client.build()
//    }

//    @Provides
//    @Singleton
//    fun providesGson(): Gson {
//        return Gson()
//    }
//
//    @Provides
//    @Singleton
//    fun providesGsonConverterFactory(): GsonConverterFactory {
//        return GsonConverterFactory.create()
//    }
//
//    @Provides
//    @Singleton
//    fun providesRxJavaCallAdapterFactory(): RxJava2CallAdapterFactory {
//        return RxJava2CallAdapterFactory.create()
//    }
//
//    @Singleton
//    @Provides
//    fun provideService(retrofit: Retrofit): ApiService {
//        return retrofit.create(ApiService::class.java)
//    }

    @Singleton
    @Provides
    fun provideWeatherRepository( apiService: ApiService): WeatherRepository {
        return WeatherRepositoryImp(apiService)
    }

}
