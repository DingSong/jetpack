package com.kvds.jectpack.model

import com.google.gson.annotations.SerializedName

class Response<T> {

    var reason: String = ""

    @SerializedName("result")
    var data: T? = null

    @SerializedName("error_code")
    var errorCode: Int = 0
}