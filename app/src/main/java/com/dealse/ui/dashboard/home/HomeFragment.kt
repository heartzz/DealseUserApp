package com.dealse.ui.dashboard.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.dealse.R
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(R.layout.fragment_home) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        recCategoryChipList.run {
            adapter = ChipListingAdapter(object :ChipListingAdapter.onAdapterItemClick{
                override fun onAdaptersItemClick() {
                }
            } )
        }

        recProductList.run {
            adapter = CategoryListingAdapter(object :CategoryListingAdapter.onAdapterItemClick{
                override fun onAdaptersItemClick() {
                }
            } )
        }

        recLimitedOffer.run {
            adapter = LimitedOfferAdapter(object :LimitedOfferAdapter.onAdapterItemClick{
                override fun onAdaptersItemClick() {
                }
            } )
        }
        recNear.run {
            adapter = NearbyPlacesAdapter(object :NearbyPlacesAdapter.onAdapterItemClick{
                override fun onAdaptersItemClick() {
                }
            } )
        }
        recRecom.run {
            adapter = RecommededAdapter(object :RecommededAdapter.onAdapterItemClick{
                override fun onAdaptersItemClick() {
                }
            } )
        }
    }
}