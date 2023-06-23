package com.stephanieverissimo.listbooks.presentation.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.stephanieverissimo.listbooks.model.Book
import com.stephanieverissimo.listbooks.model.BookResponse
import com.stephanieverissimo.listbooks.presentation.viewModel.BookViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @Composable
    fun BookListScreen(bookViewModel: BookViewModel, navController: NavController) {
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
               if(books.results.isNotEmpty()){
                   BookList(books = books, onItemClick = { book: Book -> selectedBook.value = book })
               }else{
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
               }
           }
        selectedBook.value?.let{ book -> BookDetailsScreen(bookId = book.id, bookViewModel = bookViewModel, navController = navController )}
       }




@Composable
fun BookDetailsScreen(bookId: Int, bookViewModel: BookViewModel, navController: NavController) {
    val book by bookViewModel.getBookId(bookId).collectAsState(null)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        book?.let { book ->
            Text(text = "Title: ${book.title}", style = TextStyle(fontSize = 20.sp))
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Authors:",
                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
            )
            book.authors.forEach { author ->
                Text(text = "- ${author.name}")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Subjects:",
                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
            )
            Text(text = "- ${book.subjects.getOrNull(0)}")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Year Birth: ${book?.authors?.firstOrNull()?.birth_year ?: ""}",
            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
        )
        Text(
            text = "Year Death: ${book?.authors?.firstOrNull()?.death_year ?: ""}",
            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.popBackStack() }) {
            Text(text = "Go Back")
        }
    }
   }


@Composable
fun BookApp(bookViewModel: BookViewModel) {
    val navController = rememberNavController()
    val navBackStackEntry = navController.currentBackStackEntryAsState()

    Box(){
            NavHost(navController = navController, startDestination = "bookList") {
                composable("bookList") {
                    BookListScreen(bookViewModel = bookViewModel, navController = navController)
                }
                composable(
                    "bookDetails/{id}",
                    arguments = listOf(navArgument("id") { type = NavType.IntType })
                ) {

                        backStackEntry ->
                    val bookId = backStackEntry.arguments?.getInt("id")
                    bookId?.let { id ->
                        BookDetailsScreen(
                            bookId = id,
                            bookViewModel = bookViewModel,
                            navController = navController
                        )
                    }
                }
            }
        if (navBackStackEntry.value?.destination?.route == "bookDetails/{id}") {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f)) // Cor e transparência da sombra
            ) {

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .align(Alignment.Center)
                    ) {
                        Text(
                            text = "Tela de Detalhes",
                            style = TextStyle(fontSize = 20.sp, color = Color.White),
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    }
            }
        }
        }

}

    @Composable
    fun BookList(books: BookResponse, onItemClick: (Book) -> Unit) {
        LazyColumn{
            items(books.results){
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

