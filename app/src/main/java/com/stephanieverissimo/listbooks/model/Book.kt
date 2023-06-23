package com.stephanieverissimo.listbooks.model

import com.google.gson.annotations.SerializedName


data class Book(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("authors")
    val authors: List<Author>,
    @SerializedName("subjects")
    val subjects: List<String>,
)

data class BookResponse(
    @SerializedName("results")
    val results: List<Book>
)

