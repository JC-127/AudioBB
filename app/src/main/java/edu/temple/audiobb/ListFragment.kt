package edu.temple.audiobb

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListFragment() : Fragment() {

    lateinit var bList: BookList
    lateinit var layout: View
    lateinit var bkListView: RecyclerView

    companion object {
        @JvmStatic
        fun newInstance(_bookList: BookList): ListFragment {

            val frag = ListFragment().apply {
                bList = _bookList
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

        bkListView = layout.findViewById(R.id.recView)
        bkListView.layoutManager = GridLayoutManager(requireContext(), 2)
        val adapter = bookAdapter(requireContext(), bList) {
            updateModel(bkListView.getChildAdapterPosition(it))
        }
        bkListView.adapter = adapter
    }

    private fun updateModel(index: Int) {
        ViewModelProvider(requireActivity())
            .get(bViewModel::class.java)
            .setSelectedBook(bList.get(index))
        (requireActivity() as DoubleLayout).selectionMade()
    }

}