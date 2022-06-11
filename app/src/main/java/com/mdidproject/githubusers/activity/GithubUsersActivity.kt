package com.mdidproject.githubusers.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.mdidproject.githubusers.R
import com.mdidproject.githubusers.`interface`.ItemAdapterCallback
import com.mdidproject.githubusers.adapter.UserListAdapter
import com.mdidproject.githubusers.data.User
import com.mdidproject.githubusers.databinding.ActivityGithubUsersBinding

class GithubUsersActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGithubUsersBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGithubUsersBinding.inflate(layoutInflater)
        setTitle(getString(R.string.github_user_title))
        setContentView(binding.root)
        showRecyclerList()
    }

    private fun showRecyclerList(){
        val listUserAdapter = UserListAdapter(User.loadData(resources))

        listUserAdapter.setAdapterItemCallback(object: ItemAdapterCallback<User>{
            override fun onItemClicked(data: User) {
                val intent = Intent(this@GithubUsersActivity, DetailUserActivity::class.java)
                intent.putExtra("DATA", data)
                startActivity(intent)
            }
        })

        binding.apply {
            rvUsers.setHasFixedSize(true)
            rvUsers.layoutManager = LinearLayoutManager(this@GithubUsersActivity)
            rvUsers.adapter = listUserAdapter
        }
    }

}