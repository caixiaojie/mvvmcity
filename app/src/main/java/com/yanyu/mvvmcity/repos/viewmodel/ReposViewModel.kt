package com.yanyu.mvvmcity.repos.viewmodel

import androidx.lifecycle.LiveData
import com.yanyu.mvvmcity.base.viewmodel.BaseViewModel
import com.yanyu.mvvmcity.repos.model.ReposItemModel
import com.yanyu.mvvmcity.repos.repository.ReposRepository

class ReposViewModel(private val mReposRepository: ReposRepository) : BaseViewModel() {

    fun searchRepos(
        query: String,
        sort: String,
        order: String,
        page: Int
    ): LiveData<List<ReposItemModel>> = emit {
        mReposRepository.searchRepos(query, sort, order, page).items
    }

    fun checkRepoStarred(owner: String, repo: String): LiveData<Boolean> = emit {
        mReposRepository.checkRepoStarred(owner, repo).code() == 204
    }

    fun starRepo(owner: String, repo: String): LiveData<Boolean> = emit {
        mReposRepository.starRepo(owner, repo).code() == 204
    }

    fun unStarRepo(owner: String, repo: String): LiveData<Boolean> = emit {
        mReposRepository.unStarRepo(owner, repo).code() == 204
    }
}
