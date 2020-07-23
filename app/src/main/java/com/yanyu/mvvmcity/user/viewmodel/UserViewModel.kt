package com.yanyu.mvvmcity.user.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yanyu.mvvmcity.base.viewmodel.BaseViewModel
import com.yanyu.mvvmcity.base.viewmodel.ErrorState
import com.yanyu.mvvmcity.checkResult
import com.yanyu.mvvmcity.config.Configs
import com.yanyu.mvvmcity.ext.errorToast
import com.yanyu.mvvmcity.ext.successToast
import com.yanyu.mvvmcity.repos.model.ReposItemModel
import com.yanyu.mvvmcity.user.model.*
import com.yanyu.mvvmcity.user.model.db.UserInfo
import com.yanyu.mvvmcity.user.repository.UserRepository

class UserViewModel(private val mUserRepository: UserRepository) : BaseViewModel() {

    //提供给xml文件进行绑定
    val mUserInfoModel = MutableLiveData<UserInfoModel>()
    val mUserInfoEntity = MutableLiveData<UserInfoEntity>()


    fun loginFast(phone: String, password: String) {
        launch {
            val result = mUserRepository.loginFast(phone, password)
            result.checkResult(
                onSuccess = {
                    successToast("登录成功")
                },onError = {
                    mStateLiveData.value = ErrorState(it)
                }
            )
        }
    }


    fun searchUsers(
        query: String,
        sort: String,
        order: String,
        page: Int
    ): LiveData<List<UserModel>> = emit {
        mUserRepository.searchUsers(query, sort, order, page).items
    }

    fun getUserInfo(user: String) {
        launch {
            mUserInfoModel.value = mUserRepository.getUserInfo(user)
        }
    }

    fun getUserPublicRepos(user: String, page: Int): LiveData<List<ReposItemModel>> = emit {
        mUserRepository.getUserPublicRepos(user, page)
    }

    fun getStarredRepos(user: String, page: Int): LiveData<List<ReposItemModel>> = emit {
        mUserRepository.getStarredRepos(user, page)
    }
}
