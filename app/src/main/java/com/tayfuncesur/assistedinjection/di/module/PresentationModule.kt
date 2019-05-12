package com.tayfuncesur.assistedinjection.di.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.tayfuncesur.assistedinjection.di.ViewModelFactory
import com.tayfuncesur.assistedinjection.ui.main.MainViewModel
import com.tayfuncesur.assistedinjection.ui.mainDetail.MainDetailViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@Module
abstract class PresentationModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(
        mainDetailViewModel: MainViewModel
    ): ViewModel


    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}


@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)