package com.tayfuncesur.assistedinjection.di.module

import com.google.gson.Gson
import com.tayfuncesur.assistedinjection.network.Service
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object NetworkModule {

    @Provides
    @Singleton
    @JvmStatic
    fun providesRetrofit() : Service{
        val retrofit = Retrofit.Builder()
            .baseUrl("https://age-of-empires-2-api.herokuapp.com/api/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()
        return retrofit.create(Service::class.java)
    }

}