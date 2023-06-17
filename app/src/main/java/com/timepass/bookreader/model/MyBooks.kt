package com.timepass.bookreader.model

import com.google.firebase.Timestamp

data class MyBooks(
                    var title: String ?= "",
                    var author: String ?= "",
                    var bookImageLink: String ?= "",
                    var bookDownloadLink: String?= "",
                    var bookDescription: String ?= "",
                    val timeStamp : Timestamp?= null,


)
