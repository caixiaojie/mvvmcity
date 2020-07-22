package com.yanyu.mvvmcity.user.activity

import android.content.Context
import android.content.Intent
import com.yanyu.mvvmcity.R
import com.yanyu.mvvmcity.base.activity.BaseDataBindActivity
import com.yanyu.mvvmcity.databinding.ActivityPhotoPreviewBinding
import kotlinx.android.synthetic.main.activity_photo_preview.*

class PhotoPreviewActivity : BaseDataBindActivity<ActivityPhotoPreviewBinding>() {

    companion object {
        private const val IMAGE_URL = "image_url"

        fun go2PhotoPreviewActivity(context: Context, url: String) {
            val intent = Intent(context, PhotoPreviewActivity::class.java)
            intent.putExtra(IMAGE_URL, url)
            context.startActivity(intent)
        }
    }

    override fun getLayoutId(): Int = R.layout.activity_photo_preview

    override fun initView() {
        mDataBind.url = intent.getStringExtra(IMAGE_URL)
        mPhotoView.setOnClickListener {
            finish()
        }
    }
}