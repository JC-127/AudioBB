package edu.temple.audiobb

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ListFragment : Fragment(), bookAdapter.ClickListener {
    // TODO: Rename and change types of parameters
    private var title:Array<String>? = null
    private var author: Array<String>? = null
    private lateinit var adaptor: bookAdapter
    lateinit var recyView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            title = it.getStringArray(ARG_PARAM1)
            author = it.getStringArray(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_list, container, false)
        initRecyclerView(view)
        return view

    }

    private fun initRecyclerView(view:View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.recView)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        adaptor = bookAdapter(title!!, author!!, this)
        recyclerView.adapter = adaptor
    }

    companion object {

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(title: Array<String>, author: Array<String>) =
            ListFragment().apply {
                arguments = Bundle().apply {
                    putStringArray(ARG_PARAM1, title)
                    putStringArray(ARG_PARAM2, author)
                }
            }

        fun onItemClick(string: String) {
            TODO ("NEEDS TO BE IMPLEMENTED")
        }
    }

    override fun onItemClick(string: String) {
        TODO("Not yet implemented")
    }
}