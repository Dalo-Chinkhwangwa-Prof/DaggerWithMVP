package com.example.seconddaggapp

class MainApplicationPresenter: Contract.PresenterContract {
    private lateinit var currentView: Contract.ViewContract

    override fun setCurrentView(view: Contract.ViewContract) {
        currentView = view
    }

    override fun getName() {
        currentView.displayName("My Name is Presenter.")
    }

}