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

import com.zqlite.android.dclib.entiry.*
import com.zqlite.android.logly.Logly
import io.reactivex.Observable
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
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
                 @Query(DiyCodeContract.TopicParams.offset) offset: Int = 0,
                 @Query(DiyCodeContract.TopicParams.limit) limit: Int = 20): Observable<List<Topic>>

    @GET(DiyCodeContract.kTopic)
    fun getTopic(@Query(DiyCodeContract.TopicParams.offset) offset: Int = 0,
                 @Query(DiyCodeContract.TopicParams.limit) limit: Int = 20): Observable<List<Topic>>

    @GET(DiyCodeContract.kNodes)
    fun getNodes(): Observable<MutableList<Node>>

    @GET(DiyCodeContract.kTopicDetail)
    fun getTopicDetail(@Path("id") topicId: Int): Observable<TopicDetail>

    @GET(DiyCodeContract.kTopicReplies)
    fun getTopicReplies(@Path("id") topicId: Int): Observable<List<TopicReply>>

    @GET(DiyCodeContract.kUserDetails)
    fun getUserDetail(@Path("login") loginName: String): Observable<UserDetail>

    @GET(DiyCodeContract.kMe)
    fun getMe():Observable<UserDetail>

    companion object Factory {
        fun create(): DiyCodeService {

            var interceptor: Interceptor = object : Interceptor {
                override fun intercept(chain: Interceptor.Chain?): Response {
                    var originRequest = chain!!.request()
                    if (originRequest.url().toString().contains(DiyCodeContract.kOAuthUrl)) {
                        return chain.proceed(originRequest)
                    }

                    if (originRequest.headers()["Authorization"] != null) {
                        return chain.proceed(originRequest)
                    }

                    var newRequest = originRequest.
                            newBuilder().
                            addHeader("Authorization", "Bearer " + "89e2156d98650c0779a20151a13f477b697990cbf645803ff0882aa6e5c5d63f").
                            build()
                    println(newRequest.headers().toString())
                    return chain.proceed(newRequest)
                }

            }

            var okHttpClient: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(DiyCodeContract.kDiyCodeApi)
                    .client(okHttpClient)
                    .build()
            return retrofit.create(DiyCodeService::class.java)
        }
    }
}