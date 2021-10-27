package edu.temple.audiobb

import java.io.Serializable

class BookList : Serializable {

    private val bList = ArrayList<Book>()

    fun add(book: Book){
        bList.add(book)
    }

    fun remove(book:Book){
        bList.remove(book)
    }

    fun get(index:Int):Book {
        return bList[index]
    }

    fun size():Int {
        return bList.size
    }


    //added for assignment 8
    fun getID(id:Int):Book{
        return bList[id];
    }

}