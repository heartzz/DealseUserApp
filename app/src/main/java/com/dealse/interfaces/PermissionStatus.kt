package com.dealse.interfaces

interface PermissionStatus {
    fun allGranted()
    fun onDenied()
    fun foreverDenied()
}