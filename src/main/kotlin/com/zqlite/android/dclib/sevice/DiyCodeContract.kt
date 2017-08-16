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

package com.zqlite.android.dclib.sevice

/**
 * Created by scott on 2017/8/10.
 */
class DiyCodeContract {

    companion object Contract {
        /************************* base url *************************/
        const val kOAuthUrl: String = "https://diycode.cc/oauth/token/"
        const val kDiyCodeApi: String = "https://diycode.cc/api/v3/"

        /************************* OAuth *************************/
        const val kGrantType: String = "grant_type"
        const val kUserName: String = "username"
        const val kPassword: String = "password"
        const val kClientId: String = "client_id"
        const val kClientSecret: String = "client_secret"

        /************************* route *************************/
        //获得话题
        const val kTopic: String = "topics.json"
        //关注话题
        const val kFollowTopic : String = "topics/{id}/follow.json"
        //取消关注话题
        const val kUnFollowTopic : String = "topics/{id}/unfollow.json"
        //获得话题的分类
        const val kNodes:String = "nodes.json"
        //获得话题详情
        const val kTopicDetail:String = "topics/{id}"
        //获得话题评论
        const val kTopicReplies:String = "topics/{id}/replies.json?limit=150"
        //用户资料
        const val kUserDetails:String = "users/{login}"
        //获取个人资料
        const val kMe :String = "users/me.json"

        //点赞
        const val kLikes : String = "likes.json"
        //收藏话题
        const val kFavorite:String = "topics/{id}/favorite.json"
        //取消收藏
        const val kunFavorite:String = "topics/{id}/unfavorite.json"

        //关注用户
        const val kFollowUser:String = "users/{login}/follow.json"
        //取消关注用户
        const val kunFollowUser:String = "users/{login}/unfollow.json"

        //关注的人
        const val kFollowing:String = "users/{login}/following.json"
        /************************* query params *************************/
        //topic

    }

    class TopicParams {
        companion object {
            const val type: String = "type"
            const val typeLastActived: String = "last_actived"
            const val typeRecent: String = "recent"
            const val typeNoReply: String = "no_reply"
            const val typePopular: String = "popular"
            const val typeExcellent: String = "excellent"

            const val nodeId: String = "node_id"
            const val offset: String = "offset"
            const val limit: String = "limit"
        }

    }

    class LikeType {
        companion object Constant{
            const val TYPE_TOPIC = "topic"
            const val TYPE_REPLY = "reply"
            const val TYPE_NEWS = "news"
        }
    }
}