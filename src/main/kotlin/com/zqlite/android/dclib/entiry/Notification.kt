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
 * Created by scott on 2017/8/20.
 */
data class Notification
(
        @SerializedName("id")val id:Int,
        @SerializedName("type") val type:String,
        @SerializedName("read") val read:Boolean,
        @SerializedName("actor") val actor:User,
        @SerializedName("mention_type") val mentionType:String,
        @SerializedName("mention") val mention:TopicReply,
        @SerializedName("topic") val topic:String,
        @SerializedName("reply") val reply:TopicReply,
        @SerializedName("node") val node:Node,
        @SerializedName("created_at") val createdAt:String,
        @SerializedName("updated_at") val updatedAt:String
)