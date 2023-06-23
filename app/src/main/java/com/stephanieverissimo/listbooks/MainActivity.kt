package com.stephanieverissimo.listbooks

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.stephanieverissimo.listbooks.presentation.ui.BookApp
import com.stephanieverissimo.listbooks.presentation.ui.BookListScreen
import com.stephanieverissimo.listbooks.presentation.viewModel.BookViewModel
import com.stephanieverissimo.listbooks.ui.theme.ListBooksTheme

class MainActivity : ComponentActivity() {
    private lateinit var bookViewModel: BookViewModel
    @SuppressLint("StateFlowValueCalledInComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bookViewModel = ViewModelProvider(this).get(BookViewModel::class.java)
        setContent {
            BookApp(bookViewModel = bookViewModel)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ListBooksTheme {
       BookApp(bookViewModel = BookViewModel())
    }
}