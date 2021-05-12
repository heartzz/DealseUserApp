package com.dealse.interfaces

import android.net.Uri

interface GetImage {
    fun getImage(_uri : Uri, _profilePath : String)
    fun onCancel()
    fun onError(_profilePath : String)
}