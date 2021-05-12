package com.dealse.ui.dashboard.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dealse.R


class RecommededAdapter(
    val onAdaptersItemClick: onAdapterItemClick

) : RecyclerView.Adapter<RecommededAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.adapter_recom, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


    }

    override fun getItemCount(): Int {
        return 30
    }

    interface onAdapterItemClick {
        fun onAdaptersItemClick()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)


}