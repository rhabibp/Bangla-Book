package com.timepass.bookreader.network

import com.timepass.bookreader.model.BooksUser
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Singleton


@Singleton
interface BookApi {

    @GET("")
    suspend fun getAllBooks(@Query("q") query: String): BooksUser

    @GET("")
    suspend fun getBookInfo(@Path("") bookId: String)


}