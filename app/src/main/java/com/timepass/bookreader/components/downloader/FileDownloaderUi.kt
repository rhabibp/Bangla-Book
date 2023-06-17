package com.timepass.bookreader.components.downloader

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@Composable
fun DownloadUi(navController: NavController, bookLink: DownloadLink ){

val context = LocalContext.current

Button(onClick = {

//        val downloadManager = context.getSystemService(DownloadManager::class.java)

    val downloader = AndroidDownloader(context)
    downloader.downloadFile(bookLink.link)
//        return@Button

}) {
    Text(text = "Download Now",
        Modifier
            .height(20.dp)
            .fillMaxWidth())

}
}
