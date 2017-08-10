package com.android.zqlite.dclib.entiry

import com.google.gson.annotations.SerializedName

/**
 * Created by scott on 2017/8/10.
 */
data class Token
(
        @SerializedName("access_token") val accessToken: String,
        @SerializedName("token_type") val tokenType: String,
        @SerializedName("expires_in") val expiresIn: String,
        @SerializedName("refresh_token") val refreshToken: String,
        @SerializedName("created_at") val createdAt: String
)