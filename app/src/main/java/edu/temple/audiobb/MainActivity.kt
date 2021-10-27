package edu.temple.audiobb

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity(), ListFragment.DoubleLayout {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        private val _Book = Book("", "")
        var doubleFragment = false
        lateinit var bookViewModel: bViewModel

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
            setTitle("AudioBB")

            bookViewModel = ViewModelProvider(this).get(bViewModel::class.java)

            doubleFragment = findViewById<FragmentContainerView>(R.id.fragmentContainerView2) != null

            //First load
            if (savedInstanceState == null) {
                bookViewModel.setSelectedBook(_Book)

                if (doubleFragment) {
                    //Don't add to back stack
                    supportFragmentManager.beginTransaction()
                        .add(R.id.fragmentContainerView1, ListFragment.newInstance(initBooks()))
                        .commit()
                } else {
                    supportFragmentManager.beginTransaction()
                        .add(R.id.fragmentContainerView1, ListFragment.newInstance(initBooks()))
                        .addToBackStack(null)
                        .commit()
                }
            }

            //Double screen
            if (doubleFragment) {

                if (supportFragmentManager.findFragmentById(R.id.fragmentContainerView1) is DetailsFragment) {
                    supportFragmentManager.popBackStack()
                }

                //If was single, and is now double
                if (supportFragmentManager.findFragmentById(R.id.fragmentContainerView2) == null) {

                    //Put RecyclerView back in fragmentContainerView1
                    supportFragmentManager.beginTransaction()
                        .add(R.id.fragmentContainerView2, DetailsFragment.newInstance())
                        .commit()
                }
            } else if (bookViewModel.getSelectedBook().value != _Book) {

                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView1, DetailsFragment.newInstance())
                    .addToBackStack(null)
                    .commit()
            }

        }

        private fun initBooks(): BookList {
            val list = BookList()
            val names = resources.getStringArray(R.array.bNames)
            val authors = resources.getStringArray(R.array.bAuthors)

            for (i in names.indices) {
                list.add(Book(names[i], authors[i]))
            }

            return list
        }

        override fun selectionMade() {
            if (!doubleFragment) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView1, DetailsFragment.newInstance())
                    .addToBackStack(null)
                    .commit()
            }
        }
    }