package com.yanyu.mvvmcity.user.activity

import android.content.Intent
import android.view.View
import androidx.lifecycle.Observer
import com.yanyu.mvvmcity.R
import com.yanyu.mvvmcity.base.activity.BaseDataBindVMActivity
import com.yanyu.mvvmcity.base.viewmodel.BaseViewModel
import com.yanyu.mvvmcity.config.Configs
import com.yanyu.mvvmcity.config.Settings
import com.yanyu.mvvmcity.databinding.ActivityLoginBinding
import com.yanyu.mvvmcity.ext.otherwise
import com.yanyu.mvvmcity.ext.yes
import com.yanyu.mvvmcity.home.activity.HomeActivity
import com.yanyu.mvvmcity.user.model.*
import com.yanyu.mvvmcity.user.model.db.User
import com.yanyu.mvvmcity.user.model.db.UserInfo
import com.yanyu.mvvmcity.user.viewmodel.UserViewModel
import com.yanyu.mvvmcity.utils.AppOpener
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class LoginActivity : BaseDataBindVMActivity<ActivityLoginBinding>() {

    private val mViewModel: UserViewModel by viewModel()

    private val mUserLoginModel: UserLoginModel by lazy { UserLoginModel() }

    override fun getLayoutId(): Int = R.layout.activity_login

    override fun getViewModel(): BaseViewModel = mViewModel

    override fun initView() {
        mSignInBt.setOnClickListener {
            login()
        }
        mOAuthLoginIv.setOnClickListener {
            loginByOAuth()
        }
    }

    override fun initData() {
        mDataBind.userLoginModel = mUserLoginModel
    }

    private fun login() {
        val username = mUserLoginModel.username.get().toString()
        val password = mUserLoginModel.password.get().toString()
        username.isEmpty().yes {
            mUserNameInputLayout.error = getString(R.string.username_not_null)
            mUserNameInputLayout.isErrorEnabled = true
        }.otherwise {
            mUserNameInputLayout.isErrorEnabled = false
            password.isEmpty().yes {
                mPasswordInputLayout.error = getString(R.string.password_not_null)
                mPasswordInputLayout.isErrorEnabled = true
            }.otherwise {
//                mPasswordInputLayout.isErrorEnabled = false
//                mSignInBt.visibility = View.GONE
//                mProgressBar.visibility = View.VISIBLE
                Settings.Account.userName = username
                Settings.Account.password = password
//                createOrGetAuthorization()
                loginUserPwd()
            }
        }

    }

    private fun loginUserPwd() {
        mViewModel.loginFast(Settings.Account.userName,Settings.Account.password)
    }

    private fun saveUserInfo(user: UserInfoEntity) {
        UserInfo(
            area = user.area,
            autograph = user.autograph,
            city = user.city,
            createBy = user.createBy,
            createTime = user.createTime,
            disable = user.disable,
            disableEndTime = user.disableEndTime,
            disableStartTime = user.disableStartTime,
            draftNum = user.draftNum,
            followNum = user.followNum,
            fsCount = user.fsCount,
            headPortrait = user.headPortrait,
            hxNickname = user.hxNickname,
            hxPassword = user.hxPassword,
            hxUsername = user.hxUsername,
            id = user.id,
            invitationCount = user.invitationCount,
            levelName = user.levelName,
            member = user.member,
            name = user.name,
            password = user.password,
            phone = user.phone,
            promotionCurrency = user.promotionCurrency,
            province = user.province,
            region = user.region,
            remark = user.remark,
            searchValue = user.searchValue,
            sex = user.sex,
            token = user.token,
            updateBy = user.updateBy,
            updateTime = user.updateTime,
            userRegin = user.userRegin,
            vipEndTime = user.vipEndTime,
            vipStartTime = user.vipStartTime,
            wxtcNo = user.wxtcNo
        ).apply {

            go2MainActivity()
        }
    }


    private fun loginByOAuth() {
        val randomState = UUID.randomUUID().toString()
        val url = "${Configs.GITHUB_BASE_URL}login/oauth/authorize" +
                "?client_id=" + Configs.CLIENT_ID +
                "&scope=" + Configs.OAUTH2_SCOPE +
                "&state=" + randomState
        AppOpener.openInBrowser(this, url)
    }

    private fun go2MainActivity() {
        Intent(this, HomeActivity::class.java).run {
            startActivity(this)
            finish()
        }
    }

    override fun handleError() {
        mSignInBt.visibility = View.VISIBLE
        mProgressBar.visibility = View.GONE
        mOAuthLoginIv.visibility = View.VISIBLE
        mOAuthProgress.visibility = View.GONE
    }
}