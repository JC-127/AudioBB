package edu.temple.audiobb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity(), ListFragment.DoubleLayout {

    private val _Book = Book("", "")
    private var doubleFragment = false
    lateinit var bkViewModel: bViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setTitle("AudioBB")

        bkViewModel = ViewModelProvider(this).get(bViewModel::class.java)

        doubleFragment = findViewById<FragmentContainerView>(R.id.fragmentContainerView2) != null

        //First load
        if (savedInstanceState == null) {
            bkViewModel.setSelectedBook(_Book)

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
        } else if (bkViewModel.getSelectedBook().value != _Book) {

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