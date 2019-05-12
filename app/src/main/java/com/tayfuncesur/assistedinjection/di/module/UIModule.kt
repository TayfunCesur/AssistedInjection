package com.tayfuncesur.assistedinjection.di.module

import com.tayfuncesur.assistedinjection.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class UIModule {

    @ContributesAndroidInjector
    abstract fun bindMainActivity() : MainActivity

}