package com.dealse.ui.shop_details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dealse.R
import kotlinx.android.synthetic.main.activity_shop_details.*

class ShopDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_details)
        recNear.run {
            adapter = ShopDetailsNearbyPlacesAdapter(object : ShopDetailsNearbyPlacesAdapter.onAdapterItemClick{
                override fun onAdaptersItemClick() {
                }
            } )
        }
    }
}