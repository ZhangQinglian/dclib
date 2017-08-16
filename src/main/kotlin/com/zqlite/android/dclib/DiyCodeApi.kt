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

package com.zqlite.android.dclib

import android.os.Environment
import com.zqlite.android.dclib.entiry.*
import com.zqlite.android.dclib.sevice.DiyCodeContract
import com.zqlite.android.dclib.sevice.DiyCodeService
import io.reactivex.Observable
import okhttp3.Response
import okhttp3.ResponseBody

/**
 * Created by scott on 2017/8/11.
 */
object DiyCodeApi:DiyCodeService.Callback{

    private var mCallback:Callback?=null

    private val service : DiyCodeService = DiyCodeService.create(this)

    interface Callback{
        fun getToken():String?
    }

    fun init(callback:Callback){
        mCallback = callback
    }

    override fun getToken(): String? {
        if(mCallback == null){
            throw RuntimeException("****************   you should init DiyCodeApi ")
        }
        return mCallback!!.getToken()
    }


    fun loadTopic(offset : Int, limit:Int) : Observable<List<Topic>>{
        return service.getTopic(offset,limit)
    }

    fun loadTopic(offset : Int, limit:Int, type:String=DiyCodeContract.TopicParams.typeLastActived, nodeId:Int) : Observable<List<Topic>>{
        return service.getTopic(type,nodeId,offset,limit)
    }

    fun followTopic(topicId:Int):Observable<ResponseBody>{
        return service.followTopic(topicId)
    }

    fun unfollowTopic(topicId:Int):Observable<ResponseBody>{
        return service.unfollowTopic(topicId)
    }

    fun favoriteTopic(topicId:Int):Observable<ResponseBody>{
        return service.favoriteTopic(topicId)
    }

    fun unFavoriteTopic(topicId:Int):Observable<ResponseBody>{
        return service.unfavoriteTopic(topicId)
    }
    fun loadNodes() : Observable<MutableList<Node>>{
        return service.getNodes()
    }

    fun loadTopicDetail(id:Int):Observable<TopicDetail>{
        return service.getTopicDetail(id)
    }

    fun loadTopicReplies(id:Int):Observable<List<TopicReply>>{
        return service.getTopicReplies(id)
    }

    fun loadUserDetail(login:String):Observable<UserDetail>{
        return service.getUserDetail(login)
    }

    fun loadMe():Observable<UserDetail>{
        return service.getMe()
    }

    fun like(id:Int,type:String):Observable<ResponseBody>{
        return service.like(id,type)
    }

    fun followUser(loginName:String):Observable<ResponseBody>{
        return service.followUser(loginName)
    }

    fun unfollowUser(loginName:String):Observable<ResponseBody>{
        return service.unfollowUser(loginName)
    }

    fun getfollowing(login: String):Observable<List<User>>{
        return service.getFollowing(login)
    }
    fun unlike(id:Int,type:String):Observable<ResponseBody>{
        return service.unlike(id,type)
    }

    fun login(login:String,password:String) : Observable<Token>{
        return service.login(DiyCodeContract.kOAuthUrl,"password",login,password,BuildConfig.CLIENT_ID,BuildConfig.CLIENT_SECRET)
    }
}