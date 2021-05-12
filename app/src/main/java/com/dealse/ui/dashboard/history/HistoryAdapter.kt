package com.dealse.ui.dashboard.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dealse.R
import com.dealse.ui.shop_details.ShopDetailsActivity
import com.dealse.utility.setSafeOnClickListener
import com.dealse.utility.start


class HistoryAdapter(
    val onAdaptersItemClick: onAdapterItemClick

) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.adapter_history, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
holder.itemView.setSafeOnClickListener {
    it.context.start<ShopDetailsActivity>()
}

    }

    override fun getItemCount(): Int {
        return 30
    }

    interface onAdapterItemClick {
        fun onAdaptersItemClick()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)


}