package com.example.seconddaggapp.di.component

import com.example.seconddaggapp.MainActivity
import com.example.seconddaggapp.MainFragment
import com.example.seconddaggapp.di.module.PresenterModule
import dagger.Component

@Component(modules = [PresenterModule::class])
interface MainPresenterComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(fragment: MainFragment)
}