package com.timepass.bookreader.components

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.timepass.bookreader.model.MyBooks
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await



class FetchDataViewModel: ViewModel(){
    private val state = mutableStateOf(MyBooks(timeStamp = Timestamp.now()))

    init {
        getData()
    }

    private fun getData (){
        viewModelScope.launch {
            state.value = getDataFromFireStore()
        }
    }
}



suspend fun getDataFromFireStore(): MyBooks{

    val db = FirebaseFirestore.getInstance()
    var bookDetails = MyBooks(timeStamp = Timestamp.now())

    try {
        db.collection("bookDetails").get().await().map {
          val result =  it.toObject(MyBooks::class.java)
            bookDetails = result
        }

    }
    catch (e: FirebaseFirestoreException){
        Log.d("error", "getData: $e")

    }
    return bookDetails

}
