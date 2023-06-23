package com.stephanieverissimo.listbooks.presentation.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stephanieverissimo.listbooks.model.Book
import com.stephanieverissimo.listbooks.model.BookResponse
import com.stephanieverissimo.listbooks.service.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import retrofit2.HttpException

class BookViewModel: ViewModel() {
    private val _books = MutableStateFlow(BookResponse(emptyList()))
    val books: StateFlow<BookResponse> = _books

 suspend fun fetchBooks(){
     viewModelScope.launch(Dispatchers.IO) {
         val fetchedBooks = RetrofitClient.bookApi.getBooks()
         Log.d("Json Response", fetchedBooks.toString())
         _books.value = fetchedBooks
     }
 }

    fun getBookId(bookId: Int): Flow<Book> = flow {
        try {
            val book = RetrofitClient.bookApi.getBooksDetails(bookId)
            emit(book)
        } catch (e: HttpException){
            throw e
            }
        }
}


