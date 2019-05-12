package com.tayfuncesur.assistedinjection

import android.app.Activity
import android.app.Application
import com.tayfuncesur.assistedinjection.di.AppComponent
import com.tayfuncesur.assistedinjection.di.DaggerAppComponent
import com.tayfuncesur.assistedinjection.di.DaggerComponentProvider
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class App : Application(), DaggerComponentProvider, HasActivityInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> {
        return androidInjector
    }


    override val component: AppComponent
        get() = DaggerAppComponent
            .builder()
            .application(this)
            .build()

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
    }
}