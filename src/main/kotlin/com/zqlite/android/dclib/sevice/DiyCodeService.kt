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
import io.reactivex.Observable
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

/**
 * Created by scott on 2017/8/10.
 */
internal interface DiyCodeService {

    interface Callback {
        fun getToken(): String?
    }

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

    @POST(DiyCodeContract.kFollowTopic)
    fun followTopic(@Path("id") topicId: Int): Observable<ResponseBody>

    @POST(DiyCodeContract.kUnFollowTopic)
    fun unfollowTopic(@Path("id") topicId: Int): Observable<ResponseBody>

    @POST(DiyCodeContract.kFavorite)
    fun favoriteTopic(@Path("id") id: Int): Observable<ResponseBody>

    @POST(DiyCodeContract.kunFavorite)
    fun unfavoriteTopic(@Path("id") id: Int): Observable<ResponseBody>

    @GET(DiyCodeContract.kNodes)
    fun getNodes(): Observable<MutableList<Node>>

    @GET(DiyCodeContract.kTopicDetail)
    fun getTopicDetail(@Path("id") topicId: Int): Observable<TopicDetail>

    @GET(DiyCodeContract.kTopicReplies)
    fun getTopicReplies(@Path("id") topicId: Int): Observable<List<TopicReply>>

    @GET(DiyCodeContract.kUserDetails)
    fun getUserDetail(@Path("login") loginName: String): Observable<UserDetail>

    @GET(DiyCodeContract.kMe)
    fun getMe(): Observable<UserDetail>

    @POST(DiyCodeContract.kLikes)
    @FormUrlEncoded
    fun like(@Field("obj_id") id: Int, @Field("obj_type") type: String): Observable<ResponseBody>

    @FormUrlEncoded
    @HTTP(method = "DELETE", path = DiyCodeContract.kLikes, hasBody = true)
    fun unlike(@Field("obj_id") id: Int, @Field("obj_type") type: String): Observable<ResponseBody>

    @POST(DiyCodeContract.kFollowUser)
    fun followUser(@Path("login") loginName: String): Observable<ResponseBody>

    @POST(DiyCodeContract.kunFollowUser)
    fun unfollowUser(@Path("login") loginName: String): Observable<ResponseBody>

    @GET(DiyCodeContract.kFollowing)
    fun getFollowing(@Path("login") loginName: String,
                     @Query(DiyCodeContract.TopicParams.offset) offset: Int = 0,
                     @Query(DiyCodeContract.TopicParams.limit) limit: Int = 20): Observable<List<User>>
    @GET(DiyCodeContract.kFollowers)
    fun getFollowers(@Path("login") loginName: String,
                     @Query(DiyCodeContract.TopicParams.offset) offset: Int = 0,
                     @Query(DiyCodeContract.TopicParams.limit) limit: Int = 20): Observable<List<User>>
    @POST(DiyCodeContract.kReplyTopic)
    @FormUrlEncoded
    fun replyTopic(@Path("id") id: Int, @Field("body") body: String): Observable<ResponseBody>

    @Multipart
    @POST(DiyCodeContract.kUploadImage)
    fun uploadPhoto(@Part partList: List<MultipartBody.Part>): Observable<ResponseBody>

    @GET(DiyCodeContract.kUserTopics)
    fun getUserTopics(@Path("login") loginName: String,
                      @Query(DiyCodeContract.TopicParams.offset) offset: Int = 0,
                      @Query(DiyCodeContract.TopicParams.limit) limit: Int = 20): Observable<List<Topic>>

    @GET(DiyCodeContract.kUserFavoriteTopics)
    fun getUserFavoriteTopics(@Path("login") loginName: String,
                              @Query(DiyCodeContract.TopicParams.offset) offset: Int = 0,
                              @Query(DiyCodeContract.TopicParams.limit) limit: Int = 20): Observable<List<Topic>>

    @POST(DiyCodeContract.kDevice)
    @FormUrlEncoded
    fun updateDevice(@Field("platform") flatform :String = "android",@Field("token") token:String):Observable<ResponseBody>

    @GET(DiyCodeContract.kNotification)
    fun getNotification(@Query("offset") offset:Int = 0,@Query("limit") limit: Int = 20):Observable<List<Notification>>

    companion object Factory {
        var mCallback: Callback? = null
        fun create(callback: Callback): DiyCodeService {
            mCallback = callback
            var interceptor: Interceptor = object : Interceptor {
                override fun intercept(chain: Interceptor.Chain?): Response {
                    var originRequest = chain!!.request()
                    if (originRequest.url().toString().contains(DiyCodeContract.kOAuthUrl)) {
                        return chain.proceed(originRequest)
                    }

                    if (originRequest.headers()["Authorization"] != null) {
                        return chain.proceed(originRequest)
                    }

                    if (mCallback!!.getToken() == null || mCallback!!.getToken()?.length == 0) {
                        return chain.proceed(originRequest)
                    }

                    var newRequest = originRequest.
                            newBuilder().
                            addHeader("Authorization", "Bearer " + mCallback!!.getToken()).
                            build()
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