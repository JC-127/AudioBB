package edu.temple.audiobb

class BookList (val bList:MutableList<Book>) {

    fun add(book:Book){
        bList.add(book)
    }

    fun remove(book:Book){
        bList.remove(book)
    }

    fun get(num:Int):Book {
        return bList.get(num)
    }

    fun size():Int {
        return bList.size
    }

}