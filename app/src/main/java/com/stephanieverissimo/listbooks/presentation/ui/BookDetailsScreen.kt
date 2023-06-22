package com.stephanieverissimo.listbooks.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.stephanieverissimo.listbooks.model.Book

@Composable
fun BookDetailsScreen (book: Book) {
    Column(modifier = Modifier
        .padding(16.dp)
        .fillMaxSize()
    ) {
        Text(text = book.title, style = MaterialTheme.typography.h6)
        Text(text = book.authors.joinToString { it.name }, style = MaterialTheme.typography.body1)
        Text(text = book.subjects.joinToString { it }, style = MaterialTheme.typography.body2)
        Text(text = book.authors.forEach { it.birth_year }.toString(), style = MaterialTheme.typography.body2)
        Text(text = book.authors.forEach { it.death_year }.toString(), style = MaterialTheme.typography.body2)

    }

}