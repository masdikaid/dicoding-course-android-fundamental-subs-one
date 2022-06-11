package com.mdidproject.githubusers.data

import android.annotation.SuppressLint
import android.content.res.Resources
import android.os.Parcelable
import com.mdidproject.githubusers.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    var username: String,
    var name: String,
    var location: String,
    var company: String,
    var followers: String,
    var following: String,
    var repository: String,
    var avatar: Int,
): Parcelable {
    companion object {
        @SuppressLint("Recycle")
        fun loadData(resources: Resources): ArrayList<User>{
            val dataUsername = resources.getStringArray(R.array.username)
            val dataName = resources.getStringArray(R.array.name)
            val dataLocation = resources.getStringArray(R.array.location)
            val dataCompany = resources.getStringArray(R.array.company)
            val dataFollowers = resources.getStringArray(R.array.followers)
            val dataFollowing = resources.getStringArray(R.array.following)
            val dataRepo = resources.getStringArray(R.array.repository)
            val dataAvatar = resources.obtainTypedArray(R.array.avatar)
            val listUser = ArrayList<User>()
            for (i in dataUsername.indices){
                val user = User(
                    dataUsername[i],
                    dataName[i],
                    dataLocation[i],
                    dataCompany[i],
                    dataFollowers[i],
                    dataFollowing[i],
                    dataRepo[i],
                    dataAvatar.getResourceId(i, -1)
                )
                listUser.add(user)
            }
            return listUser

        }
    }
}
