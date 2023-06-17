package com.timepass.bookreader.components.downloader

interface Downloader {

    fun downloadFile(url: String): Long

}