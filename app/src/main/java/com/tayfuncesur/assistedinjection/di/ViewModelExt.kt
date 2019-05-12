package com.tayfuncesur.assistedinjection.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity


@Suppress("UNCHECKED_CAST")
inline fun <reified T : ViewModel> AppCompatActivity.getViewModel(crossinline factory: () -> T) = T::class.java.let {
    ViewModelProviders.of(this, object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>) = factory() as T
    }).get(it)
}

inline fun <reified T : ViewModel> viewModel(activity: AppCompatActivity, crossinline initializer: () -> T) =
    lazy(LazyThreadSafetyMode.NONE) { activity.getViewModel { initializer() } }
