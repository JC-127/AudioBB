package edu.temple.audiobb

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var bookContainer : MutableList<Book> = listOf(
            Book("Book 1","Author 1"),
            Book("Book 1","Author 1"),
            Book("Book 1","Author 1"),
            Book("Book 1","Author 1"),
            Book("Book 1","Author 1"),
            Book("Book 1","Author 1"),
            Book("Book 1","Author 1"),
            Book("Book 1","Author 1"),
            Book("Book 1","Author 1"),
            Book("Book 1","Author 1")
        ) as MutableList<Book>

        var newBooks = BookList(bookContainer)
        var b : String = newBooks.get(0).author

    }
}