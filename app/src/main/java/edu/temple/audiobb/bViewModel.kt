package edu.temple.audiobb

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class bViewModel : ViewModel() {

    private val selBook: MutableLiveData<Book> by lazy {
        MutableLiveData<Book>()
    }

    fun getSelectedBook(): LiveData<Book> {
        return selBook
    }

    fun setSelectedBook(book: Book) {
        selBook.value = book
    }


}