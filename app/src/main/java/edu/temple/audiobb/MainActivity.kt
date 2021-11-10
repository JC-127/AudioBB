package edu.temple.audiobb

import android.app.Activity
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity(), ListFragment.DoubleLayout {

    var isTwoPane = false
    var resultBookList = BookList()
    val selectedBookViewModel : SelectedBookViewModel by lazy {
        ViewModelProvider(this).get(SelectedBookViewModel::class.java)
    }

    private val searchActivityLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result -> if(result.resultCode == Activity.RESULT_OK){
        resultBookList = result.data?.getSerializableExtra("bookList") as BookList
        Log.d("Result Book List",  resultBookList.toString())
    }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        isTwoPane = findViewById<View>(R.id.fragmentContainerView2) == null

        findViewById<Button>(R.id.mainSearchButton).setOnClickListener{
            searchActivityLauncher.launch(Intent(this, BookSearchActivity::class.java))
            Log.d("Main Search Btn Clicked", "Search Activity Launched")
        }

        // If this is the first time the activity is loading, go ahead and add a BookListFragment
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainerView1, ListFragment.newInstance(resultBookList))
                .commit()
        } else
        // If activity loaded previously, there's already a BookListFragment
        // If we have a single container and a selected book, place it on top
            if (isTwoPane && selectedBookViewModel.getSelectedBook().value != null) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView1, DetailsFragment())
                    .setReorderingAllowed(true)
                    .addToBackStack(null)
                    .commit()
            }

        // If we're switching from one container to two containers
        // clear BookDetailsFragment from container1
        if (supportFragmentManager.findFragmentById(R.id.fragmentContainerView1) is DetailsFragment) {
            supportFragmentManager.popBackStack()
        }

        // If we have two containers but no BookDetailsFragment, add one to container2
        if (!isTwoPane && supportFragmentManager.findFragmentById(R.id.fragmentContainerView2) !is DetailsFragment)
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainerView2, DetailsFragment())
                .commit()
    }

    override fun onBackPressed() {
        // Back press clears the selected book
        selectedBookViewModel.setSelectedBook(null)
        super.onBackPressed()
    }

    fun bookSelected() {
        // Perform a fragment replacement if we only have a single container
        // when a book is selected

        if (isTwoPane) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView1, DetailsFragment())
                .setReorderingAllowed(true)
                .addToBackStack(null)
                .commit()
        }
    }

    override fun selectionMade() {
        TODO("Not yet implemented")
    }
}