package com.tayfuncesur.assistedinjection.ui.main

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tayfuncesur.assistedinjection.R
import com.tayfuncesur.assistedinjection.model.Civilization
import com.thekhaeng.pushdownanim.PushDownAnim
import kotlinx.android.synthetic.main.single_row.view.*

class MainAdapter(val list: List<Civilization>, val onClick: (Civilization) -> Unit) :
    RecyclerView.Adapter<MainAdapter.SingleRow>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): SingleRow {
        return SingleRow(LayoutInflater.from(p0.context).inflate(R.layout.single_row, p0, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(p0: SingleRow, p1: Int) {
        p0.bind(p1)
    }


    inner class SingleRow(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(pos: Int) {
            if (pos % 2 == 0) {
                view.mainRow.setBackgroundColor(Color.parseColor("#FFFFFF"))
            } else {
                view.mainRow.setBackgroundColor(Color.parseColor("#E0E0E0"))
            }

            view.name.text = list[pos].name

            PushDownAnim.setPushDownAnimTo(view.mainRow).setScale(PushDownAnim.MODE_STATIC_DP, 5F).setOnClickListener {
                onClick.invoke(list[pos])
            }

        }
    }

}