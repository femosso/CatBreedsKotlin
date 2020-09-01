package com.example.catbreeds.di

import com.example.catbreeds.data.remote.ApiInterface
import com.example.catbreeds.data.remote.CatBreedRemoteDataSource
import com.example.catbreeds.data.repository.Repository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
        .baseUrl("https://api.thecatapi.com")
        .client(
            OkHttpClient().newBuilder()
                .addNetworkInterceptor { chain: Interceptor.Chain ->
                    chain.proceed(
                        chain.request().newBuilder()
                            .addHeader("x-api-key", "5906c13a-590c-4a4e-adf8-387e369fe4d6").build()
                    )
                }.build()
        )
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideCharacterService(retrofit: Retrofit): ApiInterface =
        retrofit.create(ApiInterface::class.java)

    @Singleton
    @Provides
    fun provideCharacterRemoteDataSource(apiInterface: ApiInterface) =
        CatBreedRemoteDataSource(apiInterface)

    @Singleton
    @Provides
    fun provideRepository(
        remoteDataSource: CatBreedRemoteDataSource
    ) =
        Repository(remoteDataSource)
}