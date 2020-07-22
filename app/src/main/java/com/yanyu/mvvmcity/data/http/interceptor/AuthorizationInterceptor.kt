package com.yanyu.mvvmcity.data.http.interceptor

import android.util.Base64
import com.yanyu.mvvmcity.config.Settings
import com.yanyu.mvvmcity.constant.Constant
import com.yanyu.mvvmcity.ext.isLogin
import com.yanyu.mvvmcity.ext.otherwise
import com.yanyu.mvvmcity.ext.yes
import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()

        return request.url.pathSegments.contains(Constant.DOWNLOAD).yes {//下载Apk，不需要添加请求头
            chain.proceed(request)
        }.otherwise {
            chain.proceed(request.newBuilder().apply {
                when {
                    request.url.pathSegments.contains(Constant.AUTHORIZATIONS) -> {//登陆授权/退出接口
                        val userCredentials =
                            "${Settings.Account.userName}:${Settings.Account.password}"
                        val auth = "basic ${Base64.encodeToString(
                            userCredentials.toByteArray(),
                            Base64.NO_WRAP
                        )}"
                        addHeader(Constant.AUTHORIZATION, auth)
                    }
                    isLogin() -> {//权限接口
                        val auth = "Token ${Settings.Account.token}"
                        addHeader(Constant.AUTHORIZATION, auth)
                    }
                }

            }.build())
        }
    }
}