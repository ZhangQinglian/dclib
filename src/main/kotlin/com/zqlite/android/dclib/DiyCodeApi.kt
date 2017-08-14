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

import com.zqlite.android.dclib.entiry.Node
import com.zqlite.android.dclib.entiry.Topic
import com.zqlite.android.dclib.entiry.TopicDetail
import com.zqlite.android.dclib.entiry.TopicReply
import com.zqlite.android.dclib.sevice.DiyCodeContract
import com.zqlite.android.dclib.sevice.DiyCodeService
import io.reactivex.Observable

/**
 * Created by scott on 2017/8/11.
 */
object DiyCodeApi {
    private val service : DiyCodeService = DiyCodeService.create()

    fun loadTopic(offset : Int, limit:Int) : Observable<List<Topic>>{
        return service.getTopic(offset,limit)
    }

    fun loadTopic(offset : Int, limit:Int, type:String=DiyCodeContract.TopicParams.typeLastActived, nodeId:Int) : Observable<List<Topic>>{
        return service.getTopic(type,nodeId,offset,limit)
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
}