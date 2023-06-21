package com.stephanieverissimo.listbooks.model


data class Book(
    val id: Int,
    val title: String,
    val authors: List<Author>,
    val subjects: List<String>,
)