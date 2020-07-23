package com.yanyu.mvvmcity.base.model


data class CommonEntity<out T>(val code: Int, val msg: String, val data: T)