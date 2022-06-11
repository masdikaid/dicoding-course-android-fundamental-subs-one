package com.mdidproject.githubusers.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.mdidproject.githubusers.data.User
import com.mdidproject.githubusers.databinding.ActivityDetailUserBinding
import com.mdidproject.githubusers.helper.IntegerHelper

class DetailUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailUserBinding
    private var userData: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        userData = intent.getParcelableExtra("DATA")

        userData?.let {
            setTitle("@"+it.username.lowercase())
            binding.apply {
                userImage.setImageResource(it.avatar)
                name.text = it.name
                company.text = it.company
                address.text = it.location
                followers.text = IntegerHelper.readAbleInt(it.followers.toInt())
                following.text = IntegerHelper.readAbleInt(it.following.toInt())
                repositories.text = IntegerHelper.readAbleInt(it.repository.toInt())
                shareButton.setOnClickListener{
                    shareClick()
                }
            }
        } ?: run {
            Toast.makeText(this@DetailUserActivity, "Error Get User Data", Toast.LENGTH_SHORT).show()
            finish()
        }


        setContentView(binding.root)
    }

    private fun shareClick(){
        val intent = Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, "Hey Look that awesome ${userData?.username} Github !")
            putExtra(Intent.EXTRA_TEXT, "Hey Look that awesome ${userData?.name} Github ! \nhttps://github.com/${userData!!.username.lowercase()}")
        }

        startActivity(Intent.createChooser(intent, "Share link!"))
    }
}