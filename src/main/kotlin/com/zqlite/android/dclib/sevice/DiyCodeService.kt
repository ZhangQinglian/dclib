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

import com.zqlite.android.dclib.entiry.Token
import com.zqlite.android.dclib.entiry.Topic
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

/**
 * Created by scott on 2017/8/10.
 */
internal interface DiyCodeService {

    @FormUrlEncoded
    @POST()
    fun login(@Url url: String,
              @Field(DiyCodeContract.kGrantType) grantType: String,
              @Field(DiyCodeContract.kUserName) userName: String,
              @Field(DiyCodeContract.kPassword) password: String,
              @Field(DiyCodeContract.kClientId) clientId: String,
              @Field(DiyCodeContract.kClientSecret) clientSecret: String): Observable<Token>


    @GET(DiyCodeContract.kTopic)
    fun getTopic(@Query(DiyCodeContract.TopicParams.type) type: String,
                 @Query(DiyCodeContract.TopicParams.nodeId) nodeId: Int,
                 @Query(DiyCodeContract.TopicParams.offset) offset: Int,
                 @Query(DiyCodeContract.TopicParams.limit) limit: Int): Observable<List<Topic>>

    @GET(DiyCodeContract.kTopic)
    fun getTopic(@Query(DiyCodeContract.TopicParams.offset) offset: Int = 0,
                 @Query(DiyCodeContract.TopicParams.limit) limit: Int = 20): Observable<List<Topic>>

    companion object Factory {
        fun create(): DiyCodeService {
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(DiyCodeContract.kDiyCodeApi)
                    .build()
            return retrofit.create(DiyCodeService::class.java)
        }
    }
}