package com.tayfuncesur.assistedinjection.di

import android.app.Application
import com.squareup.inject.assisted.dagger2.AssistedModule
import com.tayfuncesur.assistedinjection.App
import com.tayfuncesur.assistedinjection.di.module.NetworkModule
import com.tayfuncesur.assistedinjection.di.module.PresentationModule
import com.tayfuncesur.assistedinjection.di.module.UIModule
import com.tayfuncesur.assistedinjection.ui.mainDetail.MainDetailViewModel
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [UIModule::class, AndroidInjectionModule::class, PresentationModule::class, NetworkModule::class, AssistedInjectModule::class])
interface AppComponent {


    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    val mainDetailViewModelFactory: MainDetailViewModel.Factory

    fun inject(app: App)


}

@AssistedModule
@Module(includes = [AssistedInject_AssistedInjectModule::class])
interface AssistedInjectModule
