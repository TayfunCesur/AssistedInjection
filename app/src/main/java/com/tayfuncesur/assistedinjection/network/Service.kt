package com.tayfuncesur.assistedinjection.network

import com.tayfuncesur.assistedinjection.model.Civilization
import com.tayfuncesur.assistedinjection.model.CivilizationList
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface Service {

    @GET("v1/civilizations")
    fun getCivilizations(): Observable<CivilizationList>

    @GET("v1/civilization/{id}")
    fun getCivilizationDetail(@Path(value = "id", encoded = true) id: Int): Observable<Civilization>

}