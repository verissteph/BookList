package com.stephanieverissimo.listbooks.presentation.ui

import androidx.compose.runtime.*
import  com.stephanieverissimo.listbooks.presentation.viewModel.BookViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


@Composable
fun BookApp2(bookViewModel: BookViewModel) {
    val navController= rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "bookList"
    ){
        composable("bookList"){
            BookListScreen(bookViewModel = bookViewModel, navController)
        }
        composable("bookDetails/{id}"){
        backStackEntry ->
            val bookId = backStackEntry.arguments?.getString("bookId")
            val book =
        }

}