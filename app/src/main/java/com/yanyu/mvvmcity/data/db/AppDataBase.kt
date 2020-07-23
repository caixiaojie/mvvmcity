package com.yanyu.mvvmcity.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yanyu.mvvmcity.user.model.db.User
import com.yanyu.mvvmcity.user.dao.UserDao
import com.yanyu.mvvmcity.user.model.db.UserInfo

@Database(entities = [UserInfo::class], version = 1,exportSchema = false)
abstract class AppDataBase : RoomDatabase(){

    abstract fun getUserDao(): UserDao
}