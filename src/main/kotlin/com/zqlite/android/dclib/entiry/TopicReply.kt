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
data class TopicReply
(
        @SerializedName("id") val id : Int,
        @SerializedName("body_html") val bodyHtml:String,
        @SerializedName("created_at") val createdAt:String,
        @SerializedName("updated_at") val updatedAt:String,
        @SerializedName("deleted") val deleted:String,
        @SerializedName("topic_id") val topicId:String,
        @SerializedName("user") val user :User,
        @SerializedName("likes_count") val likesCount:Int,
        @SerializedName("abilities") val abilities : Ability
){
    fun getSimpleDate() : String{
        return CalendarUtils().getSimpleDate(createdAt)
    }

    fun getLikeStr():String{
        return "$likesCount"
    }
}