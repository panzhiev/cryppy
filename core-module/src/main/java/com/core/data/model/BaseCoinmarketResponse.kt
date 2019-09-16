package com.core.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.Date

class BaseCoinmarketResponse<DATA_TYPE> {
    var status: ResponseStatus? = null
    var data: DATA_TYPE? = null
}

data class ResponseStatus(
    val timestamp: Date,
    @Expose
    @SerializedName("error_code")
    val code: Int,
    @Expose
    @SerializedName("error_message")
    val message: String,
    var elapsed: Long,
    @Expose
    @SerializedName("credit_count")
    val creditCount: Int
)