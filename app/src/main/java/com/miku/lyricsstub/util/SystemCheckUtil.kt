package com.miku.lyricsstub.util

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.util.NoSuchPropertyException

class SystemCheckUtil {
    companion object {
        private const val TAG = "SystemCheckUtil"

        fun isUnofficial(context: Context): Boolean {
            getProperty(context, "ro.miku.buildtype")?.let {
                if (it == "OFFICIAL" || it == "COMMUNITY") {
                    return false
                }
            }
            return true
        }

        @SuppressLint("PrivateApi")
        @Throws(NoSuchPropertyException::class)
        private fun getProperty(
            context: Context,
            key: String?,
        ): String? {
            try {
                val classLoader = context.classLoader
                val systemProperties = classLoader.loadClass("android.os.SystemProperties")
                val methodGet = systemProperties.getMethod("get", String::class.java)
                val result = methodGet.invoke(systemProperties, key) as String?
                Log.e(TAG, "$key : $result")
                return result
            } catch (e: Exception) {
                throw NoSuchPropertyException(key)
            }
        }
    }
}
