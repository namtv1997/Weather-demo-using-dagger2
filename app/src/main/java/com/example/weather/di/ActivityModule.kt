package com.example.weather.di

import com.example.weather.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ActivityScoped
    @ContributesAndroidInjector()
    abstract fun contributeMainActivity(): MainActivity

}
