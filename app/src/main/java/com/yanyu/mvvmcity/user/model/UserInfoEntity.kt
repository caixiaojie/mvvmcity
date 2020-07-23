package com.yanyu.mvvmcity.user.model

data class UserInfoEntity(
    val area: String,
    val autograph: String,
    val city: String,
    val createBy: String,
    val createTime: String,
    val disable: Int,
    val disableEndTime: String,
    val disableStartTime: String,
    val draftNum: Int,
    val followNum: Int,
    val fsCount: Int,
    val headPortrait: String,
    val hxNickname: String,
    val hxPassword: String,
    val hxUsername: String,
    val id: Int,
    val invitationCount: Int,
    val levelName: String,
    val member: Int,
    val name: String,
    val params: Params,
    val password: String,
    val phone: String,
    val promotionCurrency: Int,
    val province: String,
    val region: String,
    val remark: String,
    val searchValue: String,
    val sex: Int,
    val token: String,
    val updateBy: String,
    val updateTime: String,
    val userRegin: String,
    val vipEndTime: String,
    val vipStartTime: String,
    val wxtcNo: String
)

class Params(
)