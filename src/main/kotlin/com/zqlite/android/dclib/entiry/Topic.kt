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
 * Created by scott on 2017/8/11.
 */

data class Topic
(
        @SerializedName("id") val id: Int,
        @SerializedName("title") val title: String,
        @SerializedName("created_at") val createAt: String,
        @SerializedName("updated_at") val updateAt: String,
        @SerializedName("replied_at") val repliedAt: String?,
        @SerializedName("replies_count") val repliesCount: Int,
        @SerializedName("node_name") val nodeName: String,
        @SerializedName("node_id") val nodeId: Int,
        @SerializedName("last_reply_user_id") val lastReplyUserId: String?,
        @SerializedName("last_reply_user_login") val lastReplyUserLogin: String?,
        @SerializedName("user") val user: User,
        @SerializedName("deleted") val deleted: Boolean,
        @SerializedName("excellent") val excellent: Boolean,
        @SerializedName("abilities") val abilities: Ability
) {

    fun getCreatedAtDes(): String {
        var des: String = CalendarUtils().getTimeDes(createAt)
        if (des == "刚刚") {
            return "刚刚发布"
        } else {
            return "发布于" + CalendarUtils().getTimeDes(createAt)

        }
    }

    fun getRepliedAtDes(): String {
        if(repliedAt == null) {
            return "暂无回复"
        }
        return "最后由" + lastReplyUserLogin + "于" + CalendarUtils().getTimeDes(repliedAt) + "回复"
    }

    fun getReplyCount(): String {
        return "" + repliesCount
    }
}