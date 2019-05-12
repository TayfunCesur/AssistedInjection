package com.tayfuncesur.assistedinjection.ui.mainDetail

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.tayfuncesur.assistedinjection.R
import com.tayfuncesur.assistedinjection.di.injector
import com.tayfuncesur.assistedinjection.di.viewModel
import com.tayfuncesur.assistedinjection.state.Resource
import kotlinx.android.synthetic.main.main_detail.*

class MainDetailActivity : AppCompatActivity() {

    private val mainDetailViewModel by viewModel(this) {
        injector.mainDetailViewModelFactory.create(intent.getIntExtra(getString(R.string.selected_civilization), -1))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_detail)

        mainDetailViewModel.liveData.observe(this, Observer {
            if (it is Resource.Success) {
                var string = ""
                if (it.data != null) {
                    string =
                        " ${it.data.name} \n Expainsion: ${it.data.expansion} \n Army Type: ${it.data.army_type} \n Team Bonus: ${it.data.team_bonus}"
                }
                detail.text = string
            }
            progress.visibility = if (it is Resource.Loading) View.VISIBLE else View.GONE
        })

    }

}