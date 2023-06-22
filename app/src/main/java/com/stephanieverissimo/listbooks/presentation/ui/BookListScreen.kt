package com.stephanieverissimo.listbooks.presentation.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.stephanieverissimo.listbooks.model.Book
import com.stephanieverissimo.listbooks.presentation.viewModel.BookViewModel


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @Composable
    fun BookListScreen(bookViewModel: BookViewModel) {
        val books by bookViewModel.books.collectAsState()
        val selectedBook = remember{ mutableStateOf<Book?>(null) }
        LaunchedEffect(Unit){
            bookViewModel.fetchBooks()
        }
           Column(
               modifier = Modifier
                   .padding(16.dp)
                   .fillMaxWidth()
           ) {
               if(books.isNotEmpty()){
                   BookList(books = books, onItemClick = { book: Book -> selectedBook.value = book })
               }else{
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
               }
           }
        selectedBook.value?.let{ book -> BookDetailsScreen(book = book)}
       }




    @Composable
    fun BookList(books: List<Book>, onItemClick: (Book) -> Unit) {
        LazyColumn{
            items(books){
                book-> BookListItem(book = book, onItemClick = onItemClick)
            }
        }
    }

    @Composable
    fun BookListItem(book: Book, onItemClick: (Book) -> Unit) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .clickable { onItemClick(book) },
            elevation = 4.dp){
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = book.title, style = MaterialTheme.typography.h6)
                Text(text = book.authors.joinToString { it.name }, style = MaterialTheme.typography.body1)

            }
        }
    }

