package com.mdidproject.githubusers.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.mdidproject.githubusers.`interface`.ItemAdapterCallback
import com.mdidproject.githubusers.adapter.UserListAdapter.ListViewHolder
import com.mdidproject.githubusers.data.User
import com.mdidproject.githubusers.databinding.ItemUserBinding
import com.mdidproject.githubusers.helper.IntegerHelper

class UserListAdapter(private val listUser: ArrayList<User>): RecyclerView.Adapter<ListViewHolder>() {

    private lateinit var itemCallback: ItemAdapterCallback<User>

    class ListViewHolder(var binding: ItemUserBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(vGroup: ViewGroup, i: Int): ListViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(vGroup.context), vGroup, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, pos: Int) {
        val (_, name, _, _, follower, _, repository, avatar) = listUser[pos]
        holder.binding.apply {
            userImage.setImageResource(avatar)
            userName.text = name
            followers.text = IntegerHelper.readAbleInt(follower.toInt())
            repositories.text = IntegerHelper.readAbleInt(repository.toInt())
            holder.itemView.setOnClickListener {itemCallback.onItemClicked(listUser[holder.adapterPosition])}
        }
    }

    override fun getItemCount(): Int = listUser.size

    fun setAdapterItemCallback(callback: ItemAdapterCallback<User>){
        this.itemCallback = callback
    }
}