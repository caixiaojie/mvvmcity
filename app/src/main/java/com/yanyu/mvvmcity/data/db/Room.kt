package com.yanyu.mvvmcity.data.db

import androidx.room.Room
import com.yanyu.mvvmcity.AppContext

private const val DB_NAME = "open_github_db"

val room = Room.databaseBuilder(AppContext, AppDataBase::class.java, DB_NAME).build()

val userDao = room.getUserDao()