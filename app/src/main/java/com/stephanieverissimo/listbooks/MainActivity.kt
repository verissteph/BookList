package com.stephanieverissimo.listbooks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.stephanieverissimo.listbooks.presentation.ui.BookListScreen
import com.stephanieverissimo.listbooks.presentation.viewModel.BookViewModel
import com.stephanieverissimo.listbooks.ui.theme.ListBooksTheme

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BookApp()
        }
    }
}

@Composable
fun BookApp() {
    val bookViewModel : BookViewModel = viewModel()
    BookListScreen(bookViewModel = bookViewModel)
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ListBooksTheme {
       BookApp()
    }
}