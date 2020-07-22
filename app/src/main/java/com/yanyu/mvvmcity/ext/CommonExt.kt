package com.yanyu.mvvmcity.ext

import com.yanyu.mvvmcity.config.Settings

fun isLogin(): Boolean = !Settings.Account.token.isBlank()

