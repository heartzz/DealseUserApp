package com.dealse.utility.permissions

import android.app.Activity
import android.content.Intent
import com.dealse.interfaces.PermissionStatus
import androidx.core.content.PermissionChecker

@PermissionChecker.PermissionResult
class PermissionChecker(mContext: Activity, _permissionStatus: PermissionStatus, vararg permission: String) {

    init {
        permissionStatus = _permissionStatus
        allPermission = permission.toList()
        mContext.startActivity(Intent(mContext, PermissionCheckerActivity::class.java))
    }

    companion object {
        lateinit var allPermission: List<String>
        const val REQ_PERMISSION_CODE = 1
        lateinit var permissionStatus: PermissionStatus

    }

}