package com.stephanieverissimo.listbooks.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stephanieverissimo.listbooks.model.Book
import com.stephanieverissimo.listbooks.model.BookResponse
import com.stephanieverissimo.listbooks.service.BookApi
import com.stephanieverissimo.listbooks.service.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class BookViewModel: ViewModel() {
    private val _books = MutableStateFlow<List<Book>>(emptyList())
    val books: StateFlow<List<Book>> = _books
    private val _selectedBook = MutableStateFlow<Book?>(null)
    val selecetedBook: StateFlow<Book> = _selectedBook.asStateFlow() as StateFlow<Book>


    fun fetchBooks(){
        viewModelScope.launch {
            try{
               val books = RetrofitClient.bookApi.getBooks()
               _books.emit(books)
            }catch (error: Error){
                // tratamento de erro
            }
        }
    fun getBookId(bookId: String) {
        viewModelScope.launch(Dispatchers.IO) {
           try {
               val book = RetrofitClient.bookApi.getBooksDetails(bookId)
               _selectedBook.value = book
           } catch (error: Error) {
               //tratamento erro
           }
       }
    }
}
}