package com.yanyu.mvvmcity.di

import com.yanyu.mvvmcity.data.db.userDao
import com.yanyu.mvvmcity.data.http.*
import com.yanyu.mvvmcity.home.viewmodel.HomeViewModel
import com.yanyu.mvvmcity.home.viewmodel.ReceivedEventViewModel
import com.yanyu.mvvmcity.repos.api.ReposApi
import com.yanyu.mvvmcity.repos.repository.ReposRepository
import com.yanyu.mvvmcity.repos.viewmodel.ReposViewModel
import com.yanyu.mvvmcity.user.api.UserApi
import com.yanyu.mvvmcity.user.repository.UserRepository
import com.yanyu.mvvmcity.user.viewmodel.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { ReceivedEventViewModel(get()) }
    viewModel { ReposViewModel(get()) }
    viewModel { UserViewModel(get()) }
}

val reposModule = module {
    //factory 每次注入时都重新创建一个新的对象
    factory { ReposRepository(get()) }
    factory { UserRepository(get(), get()) }
}

val remoteModule = module {
    //single 单列注入
    single<ReposApi> { ReposService }

    single<UserApi> { UserService }
}

val localModule = module {
    single { userDao }
}

val appModule = listOf(viewModelModule, reposModule, remoteModule, localModule)