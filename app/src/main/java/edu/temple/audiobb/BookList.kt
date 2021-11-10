package edu.temple.audiobb

import java.io.Serializable

class BookList : Serializable {

    private val bList : MutableList<Book> by lazy {
        ArrayList()
    }

    fun add(book: Book){
        bList.add(book)
    }

    fun remove(book:Book){
        bList.remove(book)
    }

    operator fun get(index: Int) = bList[index]

    fun size() = bList.size

}