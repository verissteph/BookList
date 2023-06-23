package com.stephanieverissimo.listbooks.service

import com.stephanieverissimo.listbooks.model.Book
import com.stephanieverissimo.listbooks.model.BookResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface BookApi {
    @GET("books/")
    suspend fun getBooks(): BookResponse

    @GET("books/{id}")
    suspend fun getBooksDetails(@Path("id") bookId: Int):Book
}