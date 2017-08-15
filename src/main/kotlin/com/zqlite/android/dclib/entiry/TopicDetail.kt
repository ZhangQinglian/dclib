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
import com.zqlite.android.diycode.device.utils.CalendarUtils

/**
 * Created by scott on 2017/8/13.
 */
data class TopicDetail
(
        @SerializedName("id") val id: Int,
        @SerializedName("title") val title: String,
        @SerializedName("created_at") val createdAt: String,
        @SerializedName("updated_at") val updatedAt: String,
        @SerializedName("replied_at") val repliedAt: String,
        @SerializedName("replies_count") val repliedCount: Int,
        @SerializedName("node_name") val nodeName: String,
        @SerializedName("node_id") val nodeId: String,
        @SerializedName("last_reply_user_id") val lastReplyUserId: Int,
        @SerializedName("last_reply_user_login") val lastReplyUserLogin:String,
        @SerializedName("user") val user: User,
        @SerializedName("deleted") val deleted: Boolean,
        @SerializedName("excellent") val excellent: Boolean,
        @SerializedName("abilities") val abilities: Ability,
        @SerializedName("body") val body: String,
        @SerializedName("body_html") val bodyHtml: String,
        @SerializedName("hits") val hits: Int,
        @SerializedName("likes_count") val likeCount: Int,
        @SerializedName("suggested_at") val suggestedAt: String,
        @SerializedName("followed") val followed: String,
        @SerializedName("liked") val liked: String,
        @SerializedName("favorited") val favorited: String

){

    fun getSimpleDate() : String{
        return CalendarUtils().getSimpleDate(createdAt)
    }

    fun getContentWithTitle():String{
        return "### " + title + "\n"+ body
    }

    fun getHitsStr():String{
        return "$hits"
    }

    fun getLikeStr():String{
        return "$likeCount"
    }
}