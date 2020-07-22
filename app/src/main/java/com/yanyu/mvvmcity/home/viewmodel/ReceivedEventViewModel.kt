package com.yanyu.mvvmcity.home.viewmodel

import com.yanyu.mvvmcity.base.viewmodel.BaseLPagingViewModel
import com.yanyu.mvvmcity.home.model.ReceivedEventModel
import com.yanyu.mvvmcity.user.repository.UserRepository

class ReceivedEventViewModel(private val mUserRepository: UserRepository) :
    BaseLPagingViewModel<ReceivedEventModel>() {

    var user = ""

    override suspend fun getDataList(page: Int): List<ReceivedEventModel> =
        mUserRepository.queryReceivedEvents(user, page)
}