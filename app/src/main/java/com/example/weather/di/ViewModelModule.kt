package com.example.weather.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weather.presentation.main.GeoPositionSearchViewModel
import com.example.weather.presentation.main.Weather5daysViewModel
import com.example.weather.presentation.main.WeatherCurrentViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
@Suppress("UNUSED")
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(GeoPositionSearchViewModel::class)
    abstract fun geoPositionSearchViewModel(geoPositionSearchViewModel: GeoPositionSearchViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(Weather5daysViewModel::class)
    abstract fun weather5daysViewModel(weather5daysViewModel: Weather5daysViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(WeatherCurrentViewModel::class)
    abstract fun weatherCurrentViewModel(weatherCurrentViewModel: WeatherCurrentViewModel): ViewModel
}