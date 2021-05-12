package com.dealse.ui.dashboard.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.dealse.R
import com.dealse.ui.edit_profile.EditProfileActivity
import com.dealse.utility.setSafeOnClickListener
import com.dealse.utility.start
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment(R.layout.fragment_profile) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lEditProfile.setSafeOnClickListener {
            activity?.start<EditProfileActivity>()
        }
    }
}