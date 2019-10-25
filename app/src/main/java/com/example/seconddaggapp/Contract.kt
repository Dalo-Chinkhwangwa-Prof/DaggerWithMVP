package com.example.seconddaggapp

interface Contract{
    interface PresenterContract{
        fun getName()
        fun setCurrentView(view: ViewContract)
    }
    interface ViewContract{
        fun displayName(name: String)
    }
}