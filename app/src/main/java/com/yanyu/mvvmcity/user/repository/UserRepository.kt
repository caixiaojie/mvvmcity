package com.yanyu.mvvmcity.user.repository

import com.yanyu.mvvmcity.base.model.CommonEntity
import com.yanyu.mvvmcity.base.repos.BaseRepository
import com.yanyu.mvvmcity.config.Configs
import com.yanyu.mvvmcity.home.model.ReceivedEventModel
import com.yanyu.mvvmcity.home.model.ReleaseModel
import com.yanyu.mvvmcity.repos.model.ReposItemModel
import com.yanyu.mvvmcity.user.api.UserApi
import com.yanyu.mvvmcity.user.model.db.User
import com.yanyu.mvvmcity.Result
import com.yanyu.mvvmcity.user.dao.UserDao
import com.yanyu.mvvmcity.user.model.*
import com.yanyu.mvvmcity.user.model.db.UserInfo

class UserRepository(private val mUserApi: UserApi, private val mUserDao: UserDao) : BaseRepository() {

    suspend fun createOrGetAuthorization(
        authorizationReqModel: AuthorizationReqModel,
        client_id: String, fingerprint: String
    ) =
        mUserApi.createOrGetAuthorization(authorizationReqModel, client_id, fingerprint)

//    suspend fun loginFast(phone: String, password: String) = mUserApi.loginFast(phone,password)

    suspend fun loginFast(phone: String, password: String): Result<UserInfoEntity> {
        return safeApiCall(call = { requestLoginFast(phone,password) }, errorMessage = "")
    }

    private suspend fun requestLoginFast(phone: String, password: String): Result<UserInfoEntity> =
        executeResponse(mUserApi.loginFast(phone,password))




    suspend fun getAccessToken(url: String) = mUserApi.getAccessToken(url)

    suspend fun getUser(): UserModel = mUserApi.getUser()

    suspend fun searchUsers(query: String, sort: String, order: String, page: Int): UserListModel =
        mUserApi.searchUsers(query, sort, order, page)

    suspend fun getUserInfo(user: String): UserInfoModel =
        mUserApi.getUserInfo(user)

    suspend fun getUserPublicRepos(user: String, page: Int): List<ReposItemModel> =
        mUserApi.getUserPublicRepos(user, page)

    suspend fun getStarredRepos(user: String, page: Int): List<ReposItemModel> =
        mUserApi.getStarredRepos(user, page)

    suspend fun queryReceivedEvents(user: String, page: Int): List<ReceivedEventModel> =
        mUserApi.queryReceivedEvents(user, page)

    suspend fun getReleases(): List<ReleaseModel> =
        mUserApi.getReleases(Configs.OWNER, Configs.OWNER_REPO, 1)

//    suspend fun saveLocalUser(user: User) = mUserDao.insertAll(user)

    suspend fun saveLocalUserInfo(user: UserInfo) = mUserDao.insertAll(user)

//    suspend fun getLocalUsers(): List<User> = mUserDao.getAll()

//    suspend fun deleteLocalUser(user: User) = mUserDao.deleteAll(user)

}
