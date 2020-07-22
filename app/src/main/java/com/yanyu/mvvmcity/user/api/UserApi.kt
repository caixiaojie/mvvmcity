package com.yanyu.mvvmcity.user.api

import com.yanyu.mvvmcity.config.Configs
import com.yanyu.mvvmcity.home.model.ReceivedEventModel
import com.yanyu.mvvmcity.home.model.ReleaseModel
import com.yanyu.mvvmcity.repos.model.ReposItemModel
import com.yanyu.mvvmcity.user.model.*
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface UserApi {

    @PUT("authorizations/clients/{client_id}/{fingerprint}")
    suspend fun createOrGetAuthorization(
        @Body authorizationReqModel: AuthorizationReqModel, @Path("client_id") client_id: String, @Path(
            "fingerprint"
        ) fingerprint: String
    ): AuthorizationRespModel

    @POST
    @Headers("Accept: application/json")
    suspend fun getAccessToken(
        @Url url: String
    ): OauthTokenModel

    @GET("user")
    suspend fun getUser(): UserModel

    @GET("search/users")
    suspend fun searchUsers(
        @Query("q") query: String, @Query("sort") sort: String, @Query("order") order: String, @Query(
            "page"
        ) page: Int, @Query("per_page") per_page: Int = Configs.PAGE_SIZE
    ): UserListModel

    @GET("users/{user}")
    suspend fun getUserInfo(@Path("user") user: String): UserInfoModel

    @GET("users/{user}/repos")
    suspend fun getUserPublicRepos(@Path("user") user: String, @Query("page") page: Int, @Query("per_page") per_page: Int = Configs.PAGE_SIZE): List<ReposItemModel>

    @GET("users/{user}/starred")
    suspend fun getStarredRepos(@Path("user") user: String, @Query("page") page: Int, @Query("per_page") per_page: Int = Configs.PAGE_SIZE): List<ReposItemModel>

    @GET("users/{username}/received_events?")
    suspend fun queryReceivedEvents(
        @Path("username") username: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int = Configs.PAGE_SIZE
    ): List<ReceivedEventModel>


    @GET("repos/{owner}/{repo}/releases")
    suspend fun getReleases(
        @Path("owner") owner: String,
        @Path("repo") repo: String,
        @Query("page") page: Int,
        @Query("per_page") per_page: Int = Configs.PAGE_SIZE
    ): List<ReleaseModel>

}