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

package com.zqlite.android.diycode.device.utils

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by scott on 2017/8/11.
 */
class CalendarUtils {
    val sdf : SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sssZZZ", Locale.CHINA)

    val sdf_normal : SimpleDateFormat = SimpleDateFormat("yy年MM月dd日，HH:mm", Locale.CHINA)

    fun getTimeDes(time : String) : String{
        println("   time = " + time)
        val date = sdf.parse(time)

        val now = Date()

        val calendarTime = Calendar.getInstance()
        calendarTime.time = date

        val calendarNow = Calendar.getInstance()
        calendarNow.time = now

        var yearTime = calendarTime.get(Calendar.YEAR)
        var yearNow = calendarNow.get(Calendar.YEAR)

        if(yearNow > yearTime){
            return "" + (yearNow - yearTime) + "年前"
        }else{
            var monthTime = calendarTime.get(Calendar.MONTH)
            var monthNow = calendarNow.get(Calendar.MONTH)
            if(monthNow > monthTime){
                return "" + (monthNow - monthTime) + "月前"
            }else{
                var dayTime = calendarTime.get(Calendar.DATE)
                var dayNow = calendarNow.get(Calendar.DATE)
                if(dayNow>dayTime){
                    return "" + (dayNow- dayTime) + "天前"
                }else{
                    var hourTime = calendarTime.get(Calendar.HOUR)
                    var hourNow = calendarNow.get(Calendar.HOUR)
                    if(hourNow>hourTime){
                        return "" + (hourNow- hourTime) + "小时前"
                    }else{
                        return "刚刚"
                    }
                }
            }
        }
    }

    fun getSimpleDate(time :String):String{
        val date = sdf.parse(time)
        return sdf_normal.format(date)
    }
}