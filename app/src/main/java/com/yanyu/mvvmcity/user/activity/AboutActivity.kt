package com.yanyu.mvvmcity.user.activity

import com.yanyu.mvvmcity.R
import com.yanyu.mvvmcity.base.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_about.*

class AboutActivity : BaseActivity() {

    override fun getLayoutId(): Int = R.layout.activity_about

    override fun initView() {
        mToolbar.setNavigationOnClickListener { finish() }
    }
}