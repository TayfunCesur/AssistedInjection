package com.tayfuncesur.assistedinjection.di

import android.app.Activity

interface DaggerComponentProvider {

    val component: AppComponent
}

val Activity.injector get() = (application as DaggerComponentProvider).component
