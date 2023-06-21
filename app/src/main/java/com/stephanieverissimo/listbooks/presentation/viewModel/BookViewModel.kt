package com.stephanieverissimo.listbooks.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stephanieverissimo.listbooks.model.Book
import com.stephanieverissimo.listbooks.service.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BookViewModel: ViewModel() {
    private val _books = MutableStateFlow<List<Book>>(emptyList())
    val books: StateFlow<List<Book>> = _books

    fun fetchBooks(){
        viewModelScope.launch {
            try{
               val books = RetrofitClient.bookApi.getBooks()
               _books.emit(books)
            }catch (error: Error){
                // tratamento de erro
            }
        }
    }
}