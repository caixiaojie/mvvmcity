package com.yanyu.mvvmcity.user.fragment

import com.yanyu.mvvmcity.R
import com.yanyu.mvvmcity.base.fragment.BaseDataBindVMFragment
import com.yanyu.mvvmcity.base.viewmodel.BaseViewModel
import com.yanyu.mvvmcity.databinding.FragmentUserInfoBinding
import com.yanyu.mvvmcity.user.activity.UserInfoActivity
import com.yanyu.mvvmcity.user.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_user_info.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserInfoFragment : BaseDataBindVMFragment<FragmentUserInfoBinding>() {

    private val mViewModel: UserViewModel by viewModel()

    override fun getViewModel(): BaseViewModel = mViewModel

    override fun getLayoutRes(): Int = R.layout.fragment_user_info

    private lateinit var mUser: String

    override fun initView() {
        mUser = (activity as UserInfoActivity).mUserModel.login
        mSwipeRefreshLayout.setOnRefreshListener {
            initData()
        }
    }

    override fun showLoading() {
        mSwipeRefreshLayout.isRefreshing = true
    }

    override fun dismissLoading() {
        mSwipeRefreshLayout.isRefreshing = false
    }

    override fun initData() {
        mDataBind.userViewModel = mViewModel
        mViewModel.getUserInfo(mUser)
    }
}