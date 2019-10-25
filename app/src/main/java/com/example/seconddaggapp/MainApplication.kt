package com.example.seconddaggapp

import android.app.Application
import com.example.seconddaggapp.di.component.DaggerMainPresenterComponent
import com.example.seconddaggapp.di.component.MainPresenterComponent

class MainApplication : Application() {

    private lateinit var myDaggerComponent: MainPresenterComponent
    override fun onCreate() {
        super.onCreate()

        myDaggerComponent = DaggerMainPresenterComponent
                .create()
    }

    fun getDaggerPresenterComponent(): MainPresenterComponent {
        return myDaggerComponent
    }
}