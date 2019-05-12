package com.tayfuncesur.assistedinjection.ui.main

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.tayfuncesur.assistedinjection.model.Civilization
import com.tayfuncesur.assistedinjection.network.Service
import com.tayfuncesur.assistedinjection.state.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val service: Service
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val liveData: MutableLiveData<Resource<List<Civilization>>> = MutableLiveData()

    init {
        loadCivilizations()
    }

    private fun loadCivilizations() {
        liveData.postValue(Resource.Loading())
        compositeDisposable.add(
            service.getCivilizations()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    liveData.postValue(Resource.Success(it.civilizations))
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