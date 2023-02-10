package com.example.configremotefirebase

import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class CommonInfo(val awaitSend: Int = 0,
                 var maxListen: Int,
                 var nativeAdCountNew: Int = 0,
                 val activeServer: Boolean = false) : Serializable