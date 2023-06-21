package com.stephanieverissimo.listbooks.presentation.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.stephanieverissimo.listbooks.model.Author
import com.stephanieverissimo.listbooks.model.Book
import com.stephanieverissimo.listbooks.presentation.viewModel.BookViewModel

    
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @Composable
    fun BookListScreen(bookViewModel: BookViewModel) {
        val books by bookViewModel.books.collectAsState()
        LaunchedEffect(Unit){
            bookViewModel.fetchBooks()
        }
           Column(
               modifier = Modifier
                   .padding(16.dp)
                   .fillMaxWidth()
           ) {
               if(books.isNotEmpty()){
                   BookList(books = books)
               }else{
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
               }
           }
       }



    @Composable
    fun BookList(books: List<Book>) {
        LazyColumn{
            items(books){
                book-> BookListItem(book = book)
            }
        }
    }

    @Composable
    fun BookListItem(book: Book) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            elevation = 4.dp){
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = book.title, style = MaterialTheme.typography.h6)
                Text(text = book.authors.forEach { author: Author -> author.name }.toString(), style = MaterialTheme.typography.body1)

            }
        }
    }

