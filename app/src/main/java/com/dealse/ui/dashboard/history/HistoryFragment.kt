package com.dealse.ui.dashboard.history

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.dealse.R

import kotlinx.android.synthetic.main.fragment_history.*

class HistoryFragment : Fragment(R.layout.fragment_history) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recHistory.run {
            adapter = HistoryAdapter(object : HistoryAdapter.onAdapterItemClick{
                override fun onAdaptersItemClick() {
                }
            } )
        }
    }
}