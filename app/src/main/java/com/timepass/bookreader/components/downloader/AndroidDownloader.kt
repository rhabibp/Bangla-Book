package com.timepass.bookreader.components.downloader

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import androidx.core.net.toUri

class AndroidDownloader (private val context: Context):Downloader {

    private val downloadManager = context.getSystemService(DownloadManager::class.java)
   override fun downloadFile(url: String): Long {
       val fileName = Uri.parse(url).lastPathSegment

        val request = DownloadManager.Request(url.toUri())
            .setMimeType("application/pdf")
//            .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI)
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setTitle(fileName)
            .addRequestHeader("Header","Bearer <Token>")
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,fileName)

        return downloadManager.enqueue(request)

    }




}