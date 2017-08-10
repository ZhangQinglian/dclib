package com.android.zqlite.dclib


import com.android.zqlite.dclib.sevice.DiyCodeContract
import com.android.zqlite.dclib.sevice.DiyCodeService
import org.junit.Test


/**
 * Example local unit test, which will execute on the development machine (host).

 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class ExampleUnitTest {

    @Test
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
}