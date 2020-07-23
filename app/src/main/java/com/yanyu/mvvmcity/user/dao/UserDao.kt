package com.yanyu.mvvmcity.user.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.yanyu.mvvmcity.user.model.db.User
import com.yanyu.mvvmcity.user.model.db.UserInfo

@Dao
interface UserDao {

    @Query("select * from userinfo")
    suspend fun getAll(): List<UserInfo>

    @Insert
    suspend fun insertAll(vararg user: UserInfo)

    @Delete
    suspend fun deleteAll(vararg user: UserInfo)

}