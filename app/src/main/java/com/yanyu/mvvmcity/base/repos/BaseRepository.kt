package com.yanyu.mvvmcity.base.repos

import com.yanyu.mvvmcity.HttpException
import com.yanyu.mvvmcity.base.model.CommonEntity
import com.yanyu.mvvmcity.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import java.io.IOException

/**
 * Created by luyao
 * on 2019/4/10 9:41
 */
open class BaseRepository {

    suspend fun <T : Any> apiCall(call: suspend () -> CommonEntity<T>): CommonEntity<T> {
        return call.invoke()
    }

    suspend fun <T : Any> safeApiCall(call: suspend () -> Result<T>, errorMessage: String): Result<T> {
        return try {
            call()
        } catch (e: Exception) {
            // An exception was thrown when calling the API so we're converting this to an IOException
            Result.Error(HttpException(errorMessage, e))
        }
    }

    suspend fun <T : Any> executeResponse(response: CommonEntity<T>, successBlock: (suspend CoroutineScope.() -> Unit)? = null,
                                          errorBlock: (suspend CoroutineScope.() -> Unit)? = null): Result<T> {
        return coroutineScope {
            if (response.code != 200) {
                errorBlock?.let { it() }
                Result.Error(HttpException(response.code,response.msg))
            } else {
                successBlock?.let { it() }
                Result.Success(response.data)
            }
        }
    }


}