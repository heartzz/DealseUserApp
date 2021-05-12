package com.dealse.utility

import android.Manifest
import android.animation.Animator
import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.*
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.view.View
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.view.inputmethod.InputMethodManager
import com.dealse.interfaces.PermissionStatus
import com.dealse.utility.permissions.PermissionChecker

import java.text.NumberFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.acos
import kotlin.math.cos
import kotlin.math.sin
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.BitmapImageViewTarget
import es.dmoral.toasty.Toasty

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File


private val TAG = "Util"


fun openSoftKeyboard(context: Context) {
    try {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    } catch (e: NullPointerException) {
        e.printStackTrace()
    } catch (e: Exception) {
        e.printStackTrace()
    }

}

fun getNavBarHeight(mContext: Activity): Int {

    if (!hasNavBar(mContext)) {
        return 0
    }

    val resourceId = mContext.resources.getIdentifier("navigation_bar_height", "dimen", "android")
    return if (resourceId > 0) {
        mContext.resources.getDimensionPixelSize(resourceId)
    } else 0
}

internal fun hasNavBar(context: Context): Boolean {
    val decorView = (context as Activity).window.decorView
    var has = false
    decorView.setOnSystemUiVisibilityChangeListener { visibility ->
        if (visibility and View.SYSTEM_UI_FLAG_HIDE_NAVIGATION == 0) {
            // TODO: The navigation bar is visible. Make any desired
            // adjustments to your UI, such as showing the action bar or
            // other navigational controls.
            has = true

        }
    }
    return has
}

fun getStatusBarHeight(context: Activity): Int {
    var result = 0
    val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
    if (resourceId > 0) {
        result = context.resources.getDimensionPixelSize(resourceId)
    }
    return result
}


fun distanceCount(number: Float): String {
    return when {
//        number > 10000 -> "NA km"
        number >= 1000 -> "%.1f".format(number / 1000f).plus("k KM")
        else -> "%.1f".format(number).plus(" KM")
    }
}

fun currencyCount(number: Float): String {
    val v = NumberFormat.getCurrencyInstance()
    return when {
        number > 999 && number < 1000000 -> "%.1f".format(number / 1000f).plus("K")
        number > 99 -> "%.1f".format(number / 1000f).plus("k km")
        else -> "%.1f".format(number).plus(" km")
    }
}

fun View.fadeIn(millis: Long) {
    /* Animate fade in to give better feel instead of visibility View.VISIBLE */
    this.visible()
    val colorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), 0, 100)
    colorAnimation.duration = millis // milliseconds
    colorAnimation.addUpdateListener {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            this.alpha = (it.animatedValue.toString().toFloat()) / 100f
        }
    }
    colorAnimation.start()
}

fun View.fadeOut(millis: Long, isGone: Boolean = false) {
    /* Animate fade in to give better feel instead of visibility View.VISIBLE */
    this@fadeOut.visible()
    val colorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), 100, 0)
    colorAnimation.duration = millis // milliseconds
    colorAnimation.addUpdateListener {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            this.alpha = (it.animatedValue.toString().toFloat()) / 100f
        }
    }
    colorAnimation.addListener(object : Animator.AnimatorListener {
        override fun onAnimationRepeat(animation: Animator?) {
        }

        override fun onAnimationCancel(animation: Animator?) {
        }

        override fun onAnimationStart(animation: Animator?) {
        }

        override fun onAnimationEnd(animation: Animator?) {
            if (isGone) this@fadeOut.gone()
        }
    })
    colorAnimation.start()
}


fun View.fadeOut(millis: Long) {
    /* Animate fade in to give better feel instead of visibility View.VISIBLE */
    this@fadeOut.visible()
    val colorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), 100, 0)
    colorAnimation.duration = millis // milliseconds
    colorAnimation.addUpdateListener {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            this.alpha = (it.animatedValue.toString().toFloat()) / 100f
        }
    }
    colorAnimation.addListener(object : Animator.AnimatorListener{
        override fun onAnimationRepeat(animation: Animator?) {
        }

        override fun onAnimationCancel(animation: Animator?) {
        }

        override fun onAnimationStart(animation: Animator?) {
        }

        override fun onAnimationEnd(animation: Animator?) {
//            this@fadeOut.gone()
        }
    })
    colorAnimation.start()
}







fun View.scaleIn(millis: Long) {
    val anim = ScaleAnimation(
            0f, 1f, // Start and end values for the X axis scaling
            0f, 1f, // Start and end values for the Y axis scaling
            Animation.RELATIVE_TO_SELF, 0.5f, // Pivot point of X scaling
            Animation.RELATIVE_TO_SELF, 0.5f) // Pivot point of Y scaling
    anim.fillAfter = true // Needed to keep the result of the animation
    anim.duration = millis
    this.startAnimation(anim)
}

fun View.scaleInFromRight(millis: Long) {
    val anim = ScaleAnimation(
            1f, 1f, // Start and end values for the X axis scaling
            1f, 1f, // Start and end values for the Y axis scaling
            Animation.RELATIVE_TO_SELF, 1f, // Pivot point of X scaling
            Animation.RELATIVE_TO_SELF, 0.5f) // Pivot point of Y scaling
    anim.fillAfter = true // Needed to keep the result of the animation
    anim.duration = millis
    this.startAnimation(anim)
}

fun distance(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
    val theta = lon1 - lon2
    var dist = sin(deg2rad(lat1)) * sin(deg2rad(lat2)) + (cos(deg2rad(lat1))
            * cos(deg2rad(lat2))
            * cos(deg2rad(theta)))
    dist = acos(dist)
    dist = rad2deg(dist)
    dist *= 60.0 * 1.1515
    return dist
}

private fun deg2rad(deg: Double): Double {
    return deg * Math.PI / 180.0
}

private fun rad2deg(rad: Double): Double {
    return rad * 180.0 / Math.PI
}

/*make call and pass activity and mobile number*/

fun makeCall(mContext: Activity, mobile: String) {
    PermissionChecker(mContext, object : PermissionStatus {
        @SuppressLint("MissingPermission")
        override fun allGranted() {
            val callIntent = Intent(Intent.ACTION_CALL)
            callIntent.data = Uri.parse("tel:$mobile")
            mContext.startActivity(callIntent)
        }

        override fun onDenied() {
            ShowToast.show(mContext, "Please allow permission")
        }

        override fun foreverDenied() {
            SnackBar.show(mContext, false, "Please allow permission", true, "ALLOW", View.OnClickListener {
                val intent = Intent()
                intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                intent.addCategory(Intent.CATEGORY_DEFAULT)
                intent.data = Uri.parse("package:${mContext.packageName}")
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
                intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)
                mContext.startActivity(intent)
            })
        }
    }, Manifest.permission.CALL_PHONE)
}

fun gettime(originalString: String): Date? {
    var date: Date? = null
    try {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        simpleDateFormat.timeZone = TimeZone.getTimeZone("UTC")
        date = simpleDateFormat.parse(originalString)
    } catch (e: ParseException) {
        e.printStackTrace()
    }

    return date
}
fun EditText.onDone(callback: () -> Unit) {
    setOnEditorActionListener { _, actionId, _ ->
        if (actionId == EditorInfo.IME_ACTION_SEND) {
            callback.invoke()
            true
        }else if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            callback.invoke()
            true
        }
        false
    }
}
fun getCountOfDays(createdDateString: String, expireDateString: String): String {
    val dateFormat = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())

    var createdConvertedDate: Date? = null
    var expireCovertedDate: Date? = null
    var todayWithZeroTime: Date? = null
    try {
        createdConvertedDate = dateFormat.parse(createdDateString)
        expireCovertedDate = dateFormat.parse(expireDateString)

        val today = Date()

        todayWithZeroTime = dateFormat.parse(dateFormat.format(today))
    } catch (e: ParseException) {
        e.printStackTrace()
    }

    var cYear = 0
    var cMonth = 0
    var cDay = 0

    if (createdConvertedDate!!.after(todayWithZeroTime)) {
        val cCal = Calendar.getInstance()
        cCal.time = createdConvertedDate
        cYear = cCal.get(Calendar.YEAR)
        cMonth = cCal.get(Calendar.MONTH)
        cDay = cCal.get(Calendar.DAY_OF_MONTH)

    } else {
        val cCal = Calendar.getInstance()
        cCal.time = todayWithZeroTime
        cYear = cCal.get(Calendar.YEAR)
        cMonth = cCal.get(Calendar.MONTH)
        cDay = cCal.get(Calendar.DAY_OF_MONTH)
    }


    /*Calendar todayCal = Calendar.getInstance();
    int todayYear = todayCal.get(Calendar.YEAR);
    int today = todayCal.get(Calendar.MONTH);
    int todayDay = todayCal.get(Calendar.DAY_OF_MONTH);
    */

    val eCal = Calendar.getInstance()
    eCal.time = expireCovertedDate

    val eYear = eCal.get(Calendar.YEAR)
    val eMonth = eCal.get(Calendar.MONTH)
    val eDay = eCal.get(Calendar.DAY_OF_MONTH)

    val date1 = Calendar.getInstance()
    val date2 = Calendar.getInstance()

    date1.clear()
    date1.set(cYear, cMonth, cDay)
    date2.clear()
    date2.set(eYear, eMonth, eDay)

    val diff = date2.timeInMillis - date1.timeInMillis

    val dayCount = diff.toFloat() / (24 * 60 * 60 * 1000)

    return "" + dayCount.toInt() + " Days"
}
fun <T> ImageView.loadCircularImage(
    model: T,
    borderSize: Float = 0F,
    borderColor: Int = Color.WHITE
) {

    val circularProgressDrawable = CircularProgressDrawable(context)
    circularProgressDrawable.strokeWidth = 5f
    circularProgressDrawable.centerRadius = 30f
    circularProgressDrawable.start()

    Glide.with(context)
        .asBitmap()
        .load(model)
        .apply(RequestOptions.circleCropTransform())
        .placeholder(circularProgressDrawable)
        .into(object : BitmapImageViewTarget(this) {
            override fun setResource(resource: Bitmap?) {
                setImageDrawable(
                    resource?.run {
                        RoundedBitmapDrawableFactory.create(
                            resources,
                            if (borderSize > 0) {
                                createBitmapWithBorder(borderSize, borderColor)
                            } else {
                                this
                            }
                        ).apply {
                            isCircular = true
                        }
                    }
                )
            }
        })
}

fun Bitmap.createBitmapWithBorder(borderSize: Float, borderColor: Int = Color.WHITE): Bitmap {
    val borderOffset = (borderSize * 2).toInt()
    val halfWidth = width / 2
    val halfHeight = height / 2
    val circleRadius = Math.min(halfWidth, halfHeight).toFloat()
    val newBitmap = Bitmap.createBitmap(
        width + borderOffset,
        height + borderOffset,
        Bitmap.Config.ARGB_8888
    )

    // Center coordinates of the image
    val centerX = halfWidth + borderSize
    val centerY = halfHeight + borderSize

    val paint = Paint()
    val canvas = Canvas(newBitmap).apply {
        // Set transparent initial area
        drawARGB(0, 0, 0, 0)
    }

    // Draw the transparent initial area
    paint.isAntiAlias = true
    paint.style = Paint.Style.FILL
    canvas.drawCircle(centerX, centerY, circleRadius, paint)

    // Draw the image
    paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
    canvas.drawBitmap(this, borderSize, borderSize, paint)

    // Draw the createBitmapWithBorder
    paint.xfermode = null
    paint.style = Paint.Style.STROKE
    paint.color = borderColor
    paint.strokeWidth = borderSize
    canvas.drawCircle(centerX, centerY, circleRadius, paint)
    return newBitmap
}



fun getPlainBody(text: String?) =
    text?.toRequestBody("text/plain".toMediaTypeOrNull())

@Suppress("SameParameterValue")
fun getFileBody(path: String?,keyType:String) =
    path?.let {
        File(it).let { file ->
            file.asRequestBody("*/*".toMediaTypeOrNull()).let { body ->
                MultipartBody.Part.createFormData(keyType, file.name, body)
            }
        }
    }


fun showToast(mContext: Context?, error : Boolean, toastMsg: String, isLongTime : Boolean) {
    if (mContext != null) {
        if(isLongTime&&error){
            Toasty.error(mContext, toastMsg, Toast.LENGTH_LONG, true).show()
        }else if(!isLongTime && error){
            Toasty.error(mContext, toastMsg, Toast.LENGTH_SHORT, true).show()
        }else if(isLongTime&&!error){
            Toasty.success(mContext, toastMsg, Toast.LENGTH_LONG, true).show()
        }else if(!isLongTime && !error){
            Toasty.success(mContext, toastMsg, Toast.LENGTH_SHORT, true).show()
        }
    }
}
fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}
/**
 * Logout function
 * */
internal fun Context.logout() {
    prefClearAll()
//    start<LoginActivity>()
    (this as Activity).finishAffinity()
}




