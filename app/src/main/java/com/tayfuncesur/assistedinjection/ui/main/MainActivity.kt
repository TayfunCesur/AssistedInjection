package com.tayfuncesur.assistedinjection.ui.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.tayfuncesur.assistedinjection.R
import com.tayfuncesur.assistedinjection.di.ViewModelFactory
import com.tayfuncesur.assistedinjection.di.injector
import com.tayfuncesur.assistedinjection.di.viewModel
import com.tayfuncesur.assistedinjection.state.Resource
import com.tayfuncesur.assistedinjection.ui.mainDetail.MainDetailActivity
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_main)


        mainViewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)

        mainViewModel.liveData.observe(this, Observer { it ->
            if (it is Resource.Success) {
                val mainAdapter = MainAdapter(it.data!!) {
                    val intent = Intent(this@MainActivity, MainDetailActivity::class.java)
                    intent.putExtra(getString(R.string.selected_civilization), it.id)
                    startActivity(intent)
                }
                mainRecycler.adapter = mainAdapter
            }
        })
    }
}
