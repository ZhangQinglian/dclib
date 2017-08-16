/*
 *    Copyright 2017 zhangqinglian
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.zqlite.android.dclib.entiry

import com.google.gson.annotations.SerializedName

/**
 * Created by scott on 2017/8/14.
 */
data class UserDetail
(
        @SerializedName("id") val id:Int,
        @SerializedName("login") val login:String,
        @SerializedName("name") val name:String,
        @SerializedName("avatar_url") val avatarUrl:String,
        @SerializedName("location") val location:String,
        @SerializedName("company") val company:String,
        @SerializedName("twitter") val twitter:String,
        @SerializedName("website") val website:String,
        @SerializedName("bio") val bio:String,
        @SerializedName("tagline") val tagline:String,
        @SerializedName("github") val github:String,
        @SerializedName("created_at") val createAt:String,
        @SerializedName("email") val email:String,
        @SerializedName("topics_count") val topicsCount:Int,
        @SerializedName("replies_count") val repliesCount:Int,
        @SerializedName("following_count") val followingCount:Int,
        @SerializedName("followers_count") var followersCount:Int,
        @SerializedName("favorites_count") val favoritesCount:Int,
        @SerializedName("level") val level:String,
        @SerializedName("level_name") val level_name:String
){

    fun getFollowCountDes():String{
        return "$followersCount 位关注者"
    }

    fun hasBis():Boolean{
        if(bio != null && bio.isNotEmpty()){
            return true
        }
        return false
    }
}