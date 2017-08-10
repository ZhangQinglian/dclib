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

package com.android.zqlite.dclib.sevice

import com.android.zqlite.dclib.entiry.Token
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Url

/**
 * Created by scott on 2017/8/10.
 */
interface DiyCodeService {

    @FormUrlEncoded
    @POST()
    fun login(@Url url:String,
              @Field("grant_type") grantType:String,
              @Field("username")userName:String,
              @Field("password")password:String,
              @Field("client_id")clientId:String,
              @Field("client_secret")clientSecret:String): Observable<Token>


    companion object Factory{
        fun create():DiyCodeService{
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(DiyCodeContract.kDiyCodeApi)
                    .build();
            return retrofit.create(DiyCodeService::class.java)
        }
    }
}