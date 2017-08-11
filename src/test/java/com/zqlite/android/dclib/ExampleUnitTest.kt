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
            val df = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")  //yyyy-MM-dd'T'HH:mm:ss.SSSZ

                var time = "2017-08-011T12:24:06.697+08:00"
                println("   time = " + time)
                val date = df.parse(time)

                val now = Date()

                val calendarTime = Calendar.getInstance()
                calendarTime.time = date

                val caledarNow = Calendar.getInstance()
                caledarNow.time = now

                var yearTime = calendarTime.get(Calendar.YEAR)
                var yearNow = caledarNow.get(Calendar.YEAR)

                println("yearTime = " + yearTime)
                println("yearNow = " + yearNow)
                if(yearNow > yearTime){
                    println("" + (yearNow - yearTime) + "年前")
                }else{
                    var monthTime = calendarTime.get(Calendar.MONTH)
                    var monthNow = caledarNow.get(Calendar.MONTH)
                    println("monthTime = " + monthTime)
                    println("monthNow = " + monthNow)
                    if(monthNow > monthTime){
                        println("" + (monthNow - monthTime) + "月前")
                    }else{
                        var dayTime = calendarTime.get(Calendar.DATE)
                        var dayNow = caledarNow.get(Calendar.DATE)
                        println("dayTime = " + dayTime)
                        println("dayNow = " + dayNow)
                        if(dayNow>dayTime){
                            println("" + (dayNow- dayTime) + "天前")
                        }else{
                            var hourTime = calendarTime.get(Calendar.HOUR)
                            var hourNow = caledarNow.get(Calendar.HOUR)
                            println("hourTime = " + hourTime)
                            println("hourNow = " + hourNow)
                            if(hourNow>hourTime){
                                println("" + (hourNow- hourTime) + "小时前")
                            }else{
                                print("刚刚")
                            }
                        }
                    }
                }

        })

    }
}