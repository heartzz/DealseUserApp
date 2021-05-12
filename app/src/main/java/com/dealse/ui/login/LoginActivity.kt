package com.dealse.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dealse.R
import com.dealse.ui.dashboard.DashboardActivity
import com.dealse.ui.signup.SignupActivity
import com.dealse.utility.setSafeOnClickListener
import com.dealse.utility.start
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        createNewTV.setSafeOnClickListener {
            start<SignupActivity>()
        }
        bt_login.setSafeOnClickListener {
            start<DashboardActivity>()
        }
    }
}