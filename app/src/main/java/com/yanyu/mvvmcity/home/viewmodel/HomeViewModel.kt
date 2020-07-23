package com.yanyu.mvvmcity.home.viewmodel

import androidx.lifecycle.LiveData
import com.yanyu.mvvmcity.base.viewmodel.BaseViewModel
import com.yanyu.mvvmcity.home.model.ReleaseModel
import com.yanyu.mvvmcity.user.repository.UserRepository

class HomeViewModel(private val mUserRepository: UserRepository) : BaseViewModel() {

    fun deleteUser() {
        launch {
//            mUserRepository.deleteLocalUser(mUserRepository.getLocalUsers()[0])
        }
    }

    fun getReleases(): LiveData<ReleaseModel> = emit {
        mUserRepository.getReleases()[0]
    }
}
