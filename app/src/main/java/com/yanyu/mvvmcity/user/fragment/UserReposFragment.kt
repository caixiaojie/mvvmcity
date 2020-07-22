package com.yanyu.mvvmcity.user.fragment

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.yanyu.mvvmcity.BR
import com.yanyu.mvvmcity.R
import com.yanyu.mvvmcity.base.fragment.BaseListMVFragment
import com.yanyu.mvvmcity.constant.Constant
import com.yanyu.mvvmcity.databinding.LayoutReposBinding
import com.yanyu.mvvmcity.ext.otherwise
import com.yanyu.mvvmcity.ext.yes
import com.yanyu.mvvmcity.home.event.ReposStarEvent
import com.yanyu.mvvmcity.repos.activity.go2ReposDetailActivity
import com.yanyu.mvvmcity.repos.model.ReposItemModel
import com.yanyu.mvvmcity.user.viewmodel.UserViewModel
import com.github.nitrico.lastadapter.LastAdapter
import com.github.nitrico.lastadapter.Type
import com.jeremyliao.liveeventbus.LiveEventBus
import kotlinx.android.synthetic.main.common_refresh_recyclerview.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserReposFragment : BaseListMVFragment<ReposItemModel>(){

    private val mViewModel: UserViewModel by viewModel()
    private var mUserName = ""
    private var mIsFavor = false

    companion object {

        const val KEY = "key"
        const val IS_FAVOR = "is_favor"

        fun newInstance(userName: String, isFavor: Boolean = false): UserReposFragment {
            val reposFragment = UserReposFragment()
            val arguments = Bundle()
            arguments.putString(KEY, userName)
            arguments.putBoolean(IS_FAVOR, isFavor)
            reposFragment.arguments = arguments
            return reposFragment
        }
    }

    override fun getViewModel() = mViewModel

    override fun initRecyclerView() {
        val type = Type<LayoutReposBinding>(R.layout.layout_repos)
            .onClick {
                val reposItemModel = mListData[it.adapterPosition]
                go2ReposDetailActivity(mActivity,reposItemModel.html_url,reposItemModel.name,reposItemModel.owner.login)
            }
        LastAdapter(mListData, BR.item)
            .map<ReposItemModel>(type)
            .into(mRecyclerView.apply {
                layoutManager = LinearLayoutManager(mActivity)
            })
    }

    override fun initData() {
        arguments?.let {
            mUserName = it.getString(KEY).toString()
            mIsFavor = it.getBoolean(IS_FAVOR)
            initViewModelAction()
            if (mIsFavor) initStarEvent()
        }
    }

    override fun getListData() {
        mIsFavor.yes {
            mViewModel.getStarredRepos(mUserName, mPage).observe(this, mListObserver)
        }.otherwise {
            mViewModel.getUserPublicRepos(mUserName, mPage).observe(this, mListObserver)
        }
    }

    //LiveEventBus实现Android消息总线
    private fun initStarEvent() {
        LiveEventBus.get()
            .with(Constant.STAR_EVENT_KEY, ReposStarEvent::class.java)
            .observe(this, Observer {
                onRefresh(mRefreshLayout)
            })
    }
}