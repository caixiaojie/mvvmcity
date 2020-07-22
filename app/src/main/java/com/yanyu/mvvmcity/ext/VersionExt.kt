package com.yanyu.mvvmcity.ext

import com.yanyu.mvvmcity.AppContext

fun getVersionName(): String {
    return AppContext.packageManager.getPackageInfo(AppContext.packageName, 0).versionName
}