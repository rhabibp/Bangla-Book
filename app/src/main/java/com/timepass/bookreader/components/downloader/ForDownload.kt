package com.timepass.bookreader.components.downloader

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.timepass.bookreader.model.MyBooks
import java.util.Date

@Composable
fun DownloadField(context: Context) {

    val bookImageLink = remember {mutableStateOf("") }
    val title = remember {mutableStateOf("") }
    val author = remember {mutableStateOf("") }
    val bookDownloadLink = remember {mutableStateOf("") }
    val bookDescription = remember {mutableStateOf("") }


    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

    OutlinedTextField(value = title.value, onValueChange = {title.value = it},
        textStyle = TextStyle(fontSize = 18.sp, color = MaterialTheme.colors.onBackground),
        modifier = Modifier
            .padding(start = 10.dp, bottom = 10.dp, end = 10.dp)
            .fillMaxWidth(),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(0xFFFF7421),
            focusedLabelColor = Color(0xFFFF7421),
            unfocusedBorderColor = Color(0xFFFF7421)
        ), label ={ Text(text = "Book's Title")} )
    OutlinedTextField(value = author.value, onValueChange = {author.value = it},
        textStyle = TextStyle(fontSize = 18.sp, color = MaterialTheme.colors.onBackground),
        modifier = Modifier
            .padding(start = 10.dp, bottom = 10.dp, end = 10.dp)
            .fillMaxWidth(),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(0xFFFF7421),
            focusedLabelColor = Color(0xFFFF7421),
            unfocusedBorderColor = Color(0xFFFF7421)
        ),label ={ Text(text = "Book's Author")})
    OutlinedTextField(value = bookImageLink.value, onValueChange = {bookImageLink.value = it},
        textStyle = TextStyle(fontSize = 18.sp, color = MaterialTheme.colors.onBackground),
        modifier = Modifier
            .padding(start = 10.dp, bottom = 10.dp, end = 10.dp)
            .fillMaxWidth(),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(0xFFFF7421),
            focusedLabelColor = Color(0xFFFF7421),
            unfocusedBorderColor = Color(0xFFFF7421)
        ),label ={ Text(text = "Book's Image Link")})
    OutlinedTextField(value = bookDownloadLink.value, onValueChange = {bookDownloadLink.value = it},
        textStyle = TextStyle(fontSize = 18.sp, color = MaterialTheme.colors.onBackground),
        modifier = Modifier
            .padding(start = 10.dp, bottom = 10.dp, end = 10.dp)
            .fillMaxWidth(),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(0xFFFF7421),
            focusedLabelColor = Color(0xFFFF7421),
            unfocusedBorderColor = Color(0xFFFF7421)
        ),label ={ Text(text = "Book's Download Link")})
    OutlinedTextField(value = bookDescription.value, onValueChange = {bookDescription.value = it},
        textStyle = TextStyle(fontSize = 18.sp, color = MaterialTheme.colors.onBackground),
        modifier = Modifier
            .padding(start = 10.dp, bottom = 10.dp, end = 10.dp)
            .fillMaxWidth(),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(0xFFFF7421),
            focusedLabelColor = Color(0xFFFF7421),
            unfocusedBorderColor = Color(0xFFFF7421)
        ),label ={ Text(text = "Book's Description")})

        Button(onClick = {


            saveDownloadLink(title.value,author.value,bookImageLink.value,bookDownloadLink.value,bookDescription.value,context)
        }, Modifier.background(Color(0xFFFF7421))){
            Text(text = "Save Link", color = Color.White)

        }
    }
}

fun saveDownloadLink(
    title: String,
    author: String,
    bookImageLink: String,
    bookDownloadLink: String,
    bookDescription: String,
    context: Context
) {

    val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    val dbLink: CollectionReference = db.collection("bookDetails")
    val details = MyBooks(title,author,bookImageLink,bookDownloadLink,bookDescription, timeStamp = Date())


    dbLink.add(details)
        .addOnSuccessListener {
            Toast.makeText(context,"Links Saved", Toast.LENGTH_SHORT).show()
        }

}
