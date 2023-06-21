package com.stephanieverissimo.listbooks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.tooling.preview.Preview
import com.stephanieverissimo.listbooks.presentation.ui.BookListScreen
import com.stephanieverissimo.listbooks.presentation.viewModel.BookViewModel
import com.stephanieverissimo.listbooks.service.BookApi
import com.stephanieverissimo.listbooks.ui.theme.ListBooksTheme

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BookApp(bookViewModel)
        }
    }
}

@Composable
fun BookApp(bookViewModel: BookViewModel) {
    val bookViewModel: BookViewModel = remember{ viewModel()}
    BookListScreen(bookViewModel = bookViewModel)
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ListBooksTheme {
       BookApp(viewModel())
    }
}