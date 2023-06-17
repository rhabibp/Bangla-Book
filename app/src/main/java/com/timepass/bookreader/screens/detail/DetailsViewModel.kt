package com.timepass.bookreader.screens.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.timepass.bookreader.model.MyBooks

class DetailsViewModel (): ViewModel(){
    var bookList by mutableStateOf<MyBooks?>(null)
        private set


    fun addDetails(title: MyBooks){
        bookList = title

    }
}