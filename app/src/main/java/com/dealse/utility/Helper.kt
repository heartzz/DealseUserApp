package com.dealse.utility


import android.annotation.SuppressLint
import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.graphics.Typeface
import android.os.Build
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.InputMethodManager
import org.json.JSONException
import org.json.JSONObject
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("StaticFieldLeak")
object Helper {
    private val TAG = "Helper"
    var rootView: View? = null
    private val CountryCode = ""
    private val FONT_REGULAR: Typeface? = null
    private val FONT_BOLD: Typeface? = null
    private val FONT_SEMI_BOLD: Typeface? = null



    fun hideKeyboard(context: Activity) {
        val view = context.currentFocus
        if (view != null) {
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    fun showKeyboard(context: Activity) {
        val view = context.currentFocus
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
    }

    @Throws(JSONException::class)
    fun getDataHashMap(jsonData: JSONObject): HashMap<String, String> {
        val getData =
            HashMap<String, String>()
        val keysItr = jsonData.keys()
        while (keysItr.hasNext()) {
            val key = keysItr.next()
            getData[key] = jsonData.getString(key)
        }
        return getData
    }

    fun checkEmailValidation(context: Context, email: String): Boolean {
        return !TextUtils.isEmpty(email) && !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
    fun checkMobileValidation(context: Context, mobile: String): Boolean {
        return !TextUtils.isEmpty(mobile) && !android.util.Patterns.PHONE.matcher(mobile).matches()&& !(mobile.length >10) && !(mobile.length <= 13)
    }

    fun uTCToLocal(
        dateFormatInPut: String?,
        dateFomratOutPut: String?,
        datesToConvert: String?
    ): String? {
        var dateToReturn = datesToConvert
        val sdf = SimpleDateFormat(dateFormatInPut)
        sdf.timeZone = TimeZone.getTimeZone("UTC")
        var gmt: Date? = null
        val sdfOutPutToSend =
            SimpleDateFormat(dateFomratOutPut)
        sdfOutPutToSend.timeZone = TimeZone.getDefault()
        try {
            gmt = sdf.parse(datesToConvert)
            dateToReturn = sdfOutPutToSend.format(gmt)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return dateToReturn
    }

    fun updateResources(context: Context, language: String): Boolean {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val resources = context.resources
        val configuration = resources.configuration
        configuration.locale = locale
        resources.updateConfiguration(configuration, resources.displayMetrics)
        //        AppSharedPreferences.getInstance(mContext).setString(AppSharedPreferences.PREFERENCE_LANGUAGE, language);
        return true
    }

    fun pxToDp(heght: Int, fContext: Activity): Float {
        return heght / fContext.resources.displayMetrics.density
    }

    fun getWalletDate(originalString: String): String {
        var newstr = ""
        try {
            val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            //            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            val date = simpleDateFormat.parse(originalString)
            newstr = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(date!!)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return newstr
    }

    fun getWalletDateWithTime(originalString: String): String {
        var newstr = ""
        try {
            val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            //            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            val date = simpleDateFormat.parse(originalString)
            newstr = SimpleDateFormat("dd/MM/yyyy HH:mm a", Locale.getDefault()).format(date!!)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return newstr
    }
    fun getWalletMonthDate(originalString: String): String {
        var newstr = ""
        try {
            val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            //            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            val date = simpleDateFormat.parse(originalString)
            newstr = SimpleDateFormat("dd/MMM/yyyy", Locale.getDefault()).format(date!!)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return newstr
    }

    fun getServerTimeFormatFromMilisecond(milliSeconds: Long, dateFormat: String): String {
        // Create a DateFormatter object for displaying date in specified format.
        val formatter = SimpleDateFormat(dateFormat, Locale.getDefault())
        formatter.timeZone = TimeZone.getTimeZone("UTC")

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = milliSeconds
        return formatter.format(calendar.time)
    }

    fun getDateAndTimeForOrderDetails(originalString: String): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        dateFormat.timeZone = TimeZone.getTimeZone("UTC")
        var date: Date? = null

        val dateFormat5 = SimpleDateFormat("hh:mm a',' d'th' 'of' MMMM',' yyyy", Locale.getDefault())
        //        dateFormat5.setTimeZone(TimeZone.getTimeZone("UTC"));
        try {
            date = dateFormat.parse(originalString)

            val cal = Calendar.getInstance()
            cal.time = date!!
            val day = cal.get(Calendar.DATE)

            val dateFormat1 = SimpleDateFormat("hh:mm a',' EEE d'st' MMMM',' yyyy", Locale.getDefault())
            //            dateFormat1.setTimeZone(TimeZone.getTimeZone("UTC"));

            val dateFormat2 = SimpleDateFormat("hh:mm a',' EEE d'nd' MMMM',' yyyy", Locale.getDefault())
            //            dateFormat2.setTimeZone(TimeZone.getTimeZone("UTC"));

            val dateFormat3 = SimpleDateFormat("hh:mm a',' EEE d'rd' MMMM',' yyyy", Locale.getDefault())
            //            dateFormat3.setTimeZone(TimeZone.getTimeZone("UTC"));

            val dateFormat4 = SimpleDateFormat("hh:mm a',' EEE d'th' MMMM',' yyyy", Locale.getDefault())
            //            dateFormat4.setTimeZone(TimeZone.getTimeZone("UTC"));

            if (day !in 11..18)
                return when (day % 10) {
                    1 -> dateFormat1.format(date)
                    2 -> dateFormat2.format(date)
                    3 -> dateFormat3.format(date)
                    else -> dateFormat4.format(date)
                }
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return dateFormat5.format(date!!)
    }

    fun whenDate(originalString: String?): CharSequence? {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        dateFormat.timeZone = TimeZone.getTimeZone("UTC")
        var date: Date? = null

        val dateFormat5 = SimpleDateFormat("hh:mm a',' d'th' 'of' MMMM',' yyyy", Locale.getDefault())
        //        dateFormat5.setTimeZone(TimeZone.getTimeZone("UTC"));
        try {
            date = dateFormat.parse(originalString!!)

            val cal = Calendar.getInstance()
            cal.time = date!!
            val day = cal.get(Calendar.DATE)

            val dateFormat1 = SimpleDateFormat("EEE d'st' MMMM yyyy", Locale.getDefault())
            //            dateFormat1.setTimeZone(TimeZone.getTimeZone("UTC"));

            val dateFormat2 = SimpleDateFormat("EEE d'nd' MMMM yyyy", Locale.getDefault())
            //            dateFormat2.setTimeZone(TimeZone.getTimeZone("UTC"));

            val dateFormat3 = SimpleDateFormat("EEE d'rd' MMMM yyyy", Locale.getDefault())
            //            dateFormat3.setTimeZone(TimeZone.getTimeZone("UTC"));

            val dateFormat4 = SimpleDateFormat("EEE d'th' MMMM yyyy", Locale.getDefault())
            //            dateFormat4.setTimeZone(TimeZone.getTimeZone("UTC"));

            if (day !in 11..18)
                return when (day % 10) {
                    1 -> dateFormat1.format(date)
                    2 -> dateFormat2.format(date)
                    3 -> dateFormat3.format(date)
                    else -> dateFormat4.format(date)
                }
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return dateFormat5.format(date!!)
    }

    fun convert24to12(originalString: String?): String {
        var time : String = ""
        try {
            val _24HourSDF = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
            val _12HourSDF = SimpleDateFormat("hh:mm a", Locale.getDefault())
            val _24HourDt = _24HourSDF.parse(originalString!!)
            time = _12HourSDF.format(_24HourDt!!)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return time

    }
  fun convert24to12ServerUTC(originalString: String?): String {
        var time : String = ""


      try {
          val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
          //            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
          val date = simpleDateFormat.parse(originalString)
          time = SimpleDateFormat("hh:mm a", Locale.getDefault()).format(date!!)
      } catch (e: ParseException) {
          e.printStackTrace()
      }


        return time

    }
  fun convertTimeAgoServerUTC(originalString: String?): String {
      val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
      dateFormat.timeZone = TimeZone.getTimeZone("UTC")
      var date: Date? = null

      val dateFormat5 = SimpleDateFormat("hh:mm a',' d'th' 'of' MMMM',' yyyy", Locale.getDefault())
      //        dateFormat5.setTimeZone(TimeZone.getTimeZone("UTC"));
      try {
          date = dateFormat.parse(originalString!!)

          val cal = Calendar.getInstance()
          cal.time = date!!
          val day = cal.get(Calendar.DATE)

          val dateFormat1 = SimpleDateFormat("EEE d'st' MMMM yyyy", Locale.getDefault())
          //            dateFormat1.setTimeZone(TimeZone.getTimeZone("UTC"));

          val dateFormat2 = SimpleDateFormat("EEE d'nd' MMMM yyyy", Locale.getDefault())
          //            dateFormat2.setTimeZone(TimeZone.getTimeZone("UTC"));

          val dateFormat3 = SimpleDateFormat("EEE d'rd' MMMM yyyy", Locale.getDefault())
          //            dateFormat3.setTimeZone(TimeZone.getTimeZone("UTC"));

          val dateFormat4 = SimpleDateFormat("EEE d'th' MMMM yyyy", Locale.getDefault())
          //            dateFormat4.setTimeZone(TimeZone.getTimeZone("UTC"));

          if (day !in 11..18)
              return when (day % 10) {
                  1 -> dateFormat1.format(date)
                  2 -> dateFormat2.format(date)
                  3 -> dateFormat3.format(date)
                  else -> dateFormat4.format(date)
              }
      } catch (e: ParseException) {
          e.printStackTrace()
      }

      return dateFormat5.format(date!!)

    }


    @SuppressLint("NewApi")
    fun isAppIsInBackground(context: Context): Boolean {
        var isInBackground = true
        val am = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT_WATCH) {
            val runningProcesses = am.runningAppProcesses
            for (processInfo in runningProcesses) {
                if (processInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                    for (activeProcess in processInfo.pkgList) {
                        if (activeProcess == context.packageName) {
                            isInBackground = false
                        }
                    }
                }
            }
        } else {
            val taskInfo = am.getRunningTasks(1)
            val componentInfo = taskInfo[0].topActivity
            if (componentInfo!!.packageName == context.packageName) {
                isInBackground = false
            }
        }
        return isInBackground
    }

    fun replaceLastFour(s: String): String? {
        val length = s.length
        //Check whether or not the string contains at least four characters; if not, this method is useless
        return if (length < 4) "Error: The provided string is not greater than four characters long." else s.substring(
            0,
            length - 4
        ) + "****"
    }

fun stringDateConvertToUtcDateAndReturnDateFormat(serverDate:String):Date{
    val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    val date = simpleDateFormat.parse(serverDate)
    return date
}

}
