package com.yanyu.mvvmcity.repos.repository

import com.yanyu.mvvmcity.repos.api.ReposApi
import com.yanyu.mvvmcity.repos.model.ReposListModel
import okhttp3.ResponseBody
import retrofit2.Response

class ReposRepository(private val mReposApi: ReposApi) {

    suspend fun searchRepos(query: String, sort: String,order: String, page: Int): ReposListModel =
        mReposApi.searchRepos(query, sort,order, page)

    suspend fun checkRepoStarred(owner: String, repo: String): Response<ResponseBody> =//返回原始类型
        mReposApi.checkRepoStarred(owner, repo)

    suspend fun starRepo(owner: String, repo: String): Response<ResponseBody> =
        mReposApi.starRepo(owner, repo)

    suspend fun unStarRepo(owner: String, repo: String): Response<ResponseBody> =
        mReposApi.unStarRepo(owner, repo)


}
