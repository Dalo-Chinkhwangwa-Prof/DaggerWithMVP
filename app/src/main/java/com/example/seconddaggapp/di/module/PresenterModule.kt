package com.example.seconddaggapp.di.module

import com.example.seconddaggapp.MainApplicationPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PresenterModule {

    @Provides
    fun providesMainApplicationPresenter(): MainApplicationPresenter {
        return MainApplicationPresenter()
    }

}