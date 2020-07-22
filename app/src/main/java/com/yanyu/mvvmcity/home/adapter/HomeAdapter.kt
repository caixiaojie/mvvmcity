package com.yanyu.mvvmcity.home.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.yanyu.mvvmcity.config.Configs
import com.yanyu.mvvmcity.databinding.LayoutReceivedEventBinding
import com.yanyu.mvvmcity.home.model.ReceivedEventModel
import com.yanyu.mvvmcity.repos.activity.go2ReposDetailActivity
import com.yanyu.mvvmcity.user.activity.go2UserInfoActivity
import com.yanyu.mvvmcity.user.model.UserModel

class HomeAdapter(private val mContext: Activity) :
    PagingDataAdapter<ReceivedEventModel, HomeAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ReceivedEventModel>() {
            override fun areItemsTheSame(oldItem: ReceivedEventModel, newItem: ReceivedEventModel) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: ReceivedEventModel,
                newItem: ReceivedEventModel
            ) = oldItem == newItem
        }
    }

    private val mLayoutInflater: LayoutInflater = LayoutInflater.from(mContext)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = LayoutReceivedEventBinding.inflate(mLayoutInflater, parent, false)
        return ViewHolder(binding.root, binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { receivedEventModel ->
            holder.bindData(receivedEventModel)
            holder.itemView.setOnClickListener {
                val splitArr = receivedEventModel.repo.name.split("/")
                go2ReposDetailActivity(
                    mContext, "${Configs.GITHUB_BASE_URL}${receivedEventModel.repo.name}",
                    splitArr[1], splitArr[0]
                )
            }
        }
    }

    inner class ViewHolder(itemView: View, val binding: LayoutReceivedEventBinding) :
        RecyclerView.ViewHolder(itemView) {

        fun bindData(receivedEventModel: ReceivedEventModel) {
            binding.item = receivedEventModel
            binding.ivHead.setOnClickListener {
                go2UserInfoActivity(
                    mContext,
                    binding.ivHead,
                    UserModel(receivedEventModel.actor.login, receivedEventModel.actor.avatar_url)
                )
            }
        }
    }
}