package edu.temple.audiobb

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ListFragment : Fragment() {

    lateinit var bList: BookList
    lateinit var layout: View
    lateinit var bListView: RecyclerView

    companion object {
        @JvmStatic
        fun newInstance(bkList: BookList): ListFragment {

            val frag = ListFragment().apply {
                bList = bkList
                arguments = Bundle().apply {
                    putSerializable("bookList", bList)
                }
            }
            return frag
        }
    }

    interface DoubleLayout {
        fun selectionMade()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            bList = it.getSerializable("bookList") as BookList
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        layout = inflater.inflate(R.layout.fragment_list, container, false)
        return layout
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bListView = layout.findViewById(R.id.recView)
        bListView.layoutManager = GridLayoutManager(requireContext(), 2)
        val adapter = bookAdapter(requireContext(), bList) {
            updateModel(bListView.getChildAdapterPosition(it))
        }
        bListView.adapter = adapter
    }

    private fun updateModel(index: Int) {
        ViewModelProvider(requireActivity())
            .get(bViewModel::class.java)
            .setSelectedBook(bList.get(index))
        (requireActivity() as DoubleLayout).selectionMade()
    }

}