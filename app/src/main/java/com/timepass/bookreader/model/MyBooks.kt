package com.timepass.bookreader.model

import com.google.errorprone.annotations.Keep
import java.util.Date
@Keep
data class MyBooks(
    var title: String ?= "",
    var author: String ?= "",
    var bookImageLink: String ?= "",
    var bookDownloadLink: String?= "",
    var bookDescription: String ?= "",
    var timeStamp: Date = Date(),


    )


//