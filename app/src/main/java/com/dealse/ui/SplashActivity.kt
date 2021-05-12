package com.dealse.ui

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import android.util.Base64
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.annotation.RequiresApi
import com.dealse.BaseActivity
import com.dealse.MainActivity
import com.dealse.R
import com.dealse.interfaces.PermissionStatus
import com.dealse.ui.login.LoginActivity
import com.dealse.ui.onBoarding.OnBoardingActivity
import com.dealse.utility.*
import com.dealse.utility.permissions.PermissionChecker

import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


class SplashActivity : BaseActivity() {
    lateinit var activity : SplashActivity
    lateinit var context: Context




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_splash)
        activity = this
        context = this
        onPermissionChecker()
        printHashKey(activity)
    }


    override fun onResume() {
        super.onResume()

    }


    fun printHashKey(pContext: Context) {
        try {
            val info = packageManager.getPackageInfo(
                packageName,
                PackageManager.GET_SIGNATURES
            )
            for (signature in info.signatures) {
                val md = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                Log.e("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT))
            }
        } catch (e: PackageManager.NameNotFoundException) {

        } catch (e: NoSuchAlgorithmException) {

        }
    }


    fun loadMainScreen(){

        val handler = Handler()
        handler.postDelayed({


            when {
                prefContain(AppConstant.IS_FIRST_TIME_ON_BOARDING) -> {
                    when {
                        prefContain(AppConstant.USER_ID) -> {
                            start<MainActivity>()//dash
                            finishAffinity()
                        }
                        else -> {
                            start<LoginActivity>()//login
                            finishAffinity()

                        }
                    }
                }else -> {
                    start<OnBoardingActivity>()//login
                    finishAffinity()

                }
            }


        }, 3000)


    }


    fun onPermissionChecker(){
        PermissionChecker(mContext, object : PermissionStatus {
            override fun allGranted() {
                if (mContext.isNetworkAvailable()) {
                        mContext.hideKeyboard()
                    loadMainScreen()
                }
            }

            override fun onDenied() {
                showToast(mContext, true,"Please allow location permission",false)
            }

            override fun foreverDenied() {
                showToast(mContext, true,"Please allow location permission ",false)

                val intent = Intent()
                intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                val uri = Uri.fromParts("package", mContext.getPackageName(), null)
                intent.data = uri
                mContext.startActivity(intent)

            }
        }, Manifest.permission.CAMERA)
    }



    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun setLightStatusBar() {
        val window = window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = resources.getColor(android.R.color.transparent)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

    }



}
