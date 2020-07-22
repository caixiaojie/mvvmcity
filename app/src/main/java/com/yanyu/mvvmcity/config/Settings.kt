package com.yanyu.mvvmcity.config

import com.yanyu.mvvmcitycity.constant.Constant
import com.yanyu.mvvmcitycity.data.storage.Preference

object Settings {

    object Account {
        var token by Preference(Constant.USER_TOKEN, "")
        var loginUser by Preference(Constant.LOGIN_USER, "")
        var userName by Preference(Constant.USER_NAME, "")
        var password by Preference(Constant.USER_PASSWORD, "")
    }
}