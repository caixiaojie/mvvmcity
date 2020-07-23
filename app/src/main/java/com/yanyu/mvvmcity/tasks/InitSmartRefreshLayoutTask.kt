package com.yanyu.mvvmcity.tasks

import androidx.core.content.ContextCompat
import com.yanyu.mvvmcity.R
import com.fmt.launch.starter.task.Task
import com.scwang.smart.refresh.header.MaterialHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout

class InitSmartRefreshLayoutTask : Task() {

    override fun run() {
        SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout ->
            layout.setEnableHeaderTranslationContent(false)
            MaterialHeader(context).setColorSchemeColors(
                ContextCompat.getColor(
                    context,
                    R.color.colorPrimary
                )
            )
        }
    }
}