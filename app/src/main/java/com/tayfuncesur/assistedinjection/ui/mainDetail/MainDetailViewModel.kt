package com.tayfuncesur.assistedinjection.ui.mainDetail

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import com.tayfuncesur.assistedinjection.model.Civilization
import com.tayfuncesur.assistedinjection.network.Service
import com.tayfuncesur.assistedinjection.state.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainDetailViewModel @AssistedInject constructor(
    private val service: Service,
    @Assisted val civilizationId: Int
) : ViewModel() {

    @AssistedInject.Factory
    interface Factory {
        fun create(civilizationId: Int): MainDetailViewModel
    }

    private val compositeDisposable = CompositeDisposable()

    val liveData: MutableLiveData<Resource<Civilization>> = MutableLiveData()

    init {
        loadData()
    }

    private fun loadData() {
        liveData.postValue(Resource.Loading())
        compositeDisposable.add(
            service.getCivilizationDetail(civilizationId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    liveData.postValue(Resource.Success(it))
                }, {
                    liveData.postValue(Resource.Failure(it.localizedMessage))
                })
        )
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }


}