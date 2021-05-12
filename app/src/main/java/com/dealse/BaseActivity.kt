package com.dealse
import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Context
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.content.res.Resources
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat


@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {

    protected lateinit var mContext: AppCompatActivity
    protected var TAG: String = "logCheck"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
        TAG = javaClass.name.substring(javaClass.name.lastIndexOf('.') + 1)
    }

    fun onClickBack(v: View){
        onBackPressed()
    }

    private var progressDialog: ProgressDialog? = null


    internal fun showProgressDialog(@StringRes messageId: Int) {
        if (progressDialog == null) {
            progressDialog = ProgressDialog(this)
            progressDialog!!.isIndeterminate = true
            progressDialog!!.setCancelable(false)
            progressDialog!!.setCanceledOnTouchOutside(false)

            // Disable the back button
            progressDialog!!.setOnKeyListener(KeyEventListener())
        }
        progressDialog!!.setMessage(getString(messageId))
        progressDialog!!.show()
    }

    override fun onPause() {
        super.onPause()
        hideProgressDialog()
    }

    internal fun hideProgressDialog() {
        if (progressDialog != null && progressDialog!!.isShowing) {
            progressDialog!!.dismiss()
        }
    }


    fun checkPermissions(permissions: Array<String>): Boolean {
        for (permission in permissions) {
            if (checkPermission(permission)) {
                return true
            }
        }
        return false
    }

    protected fun checkPermission(permission: String): Boolean {
        return ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_DENIED
    }

    inner class KeyEventListener : DialogInterface.OnKeyListener {
        override fun onKey(dialog: DialogInterface?, keyCode: Int, keyEvent: KeyEvent?): Boolean {
            return keyCode == KeyEvent.KEYCODE_BACK
        }
    }



}