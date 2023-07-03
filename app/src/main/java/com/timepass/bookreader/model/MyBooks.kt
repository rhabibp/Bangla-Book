package com.timepass.bookreader.model

import com.google.firebase.Timestamp

data class MyBooks(
                    var title: String ?= null,
                    var author: String ?= null,
                    var bookImageLink: String ?= null,
                    var bookDownloadLink: String?= null,
                    var bookDescription: String ?= null,
                    val timeStamp : Timestamp?= null,


)

//