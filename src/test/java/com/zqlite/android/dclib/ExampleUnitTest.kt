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


import com.zqlite.android.dclib.sevice.DiyCodeContract
import com.zqlite.android.dclib.sevice.DiyCodeService
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*


/**
 * Example local unit test, which will execute on the development machine (host).

 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class ExampleUnitTest {

    fun loginTest() {
        var service : DiyCodeService = DiyCodeService.create()
        service.login(DiyCodeContract.kOAuthUrl,
                 grantType = "password",
                 userName = BuildConfig.USERNAME,
                password = BuildConfig.PASSWORD,
                clientId = BuildConfig.CLIENT_ID,
                clientSecret = BuildConfig.CLIENT_SECRET)
                .subscribe({
                    print(it.accessToken)
                })
    }

    @Test
    fun getTopics(){
        var service : DiyCodeService = DiyCodeService.create()
        service.getTopic().subscribe({
            for (t in it){
                println(t.toString())
            }
        })
    }

    @Test
    fun getNodes(){
        var service : DiyCodeService = DiyCodeService.create()
        service.getNodes().subscribe({
            for(node in it){
                println(node.toString())
            }
        })
    }

    @Test
    fun getTopicDetail(){
        DiyCodeApi.loadTopicDetail(714).subscribe {
            println(it.toString())
        }
    }

    @Test
    fun getTopicReplies(){
        DiyCodeApi.loadTopicReplies(595).subscribe {
            for(node in it){
                println(node.toString())
            }
        }
    }
}