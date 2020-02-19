package com.example.weather.app

import com.example.weather.di.ActivityModule
import com.example.weather.di.ViewModelModule
import com.example.weather.data.source.remote.ApiModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ApiModule::class,
    ViewModelModule::class,
    ActivityModule::class
])

interface AppComponent : AndroidInjector<App> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: App): AppComponent
    }
}
