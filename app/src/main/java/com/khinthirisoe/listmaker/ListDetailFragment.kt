package com.khinthirisoe.listmaker

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class ListDetailFragment : Fragment() {

    lateinit var list: TaskList
    lateinit var listItemsRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        list = arguments!!.getParcelable(MainActivity.INTENT_LIST_KEY)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list_detail, container, false)
        view?.let {
            listItemsRecyclerView = it.findViewById<RecyclerView>(R.id.list_item_recyclerview)
            listItemsRecyclerView.adapter = ListItemsRecyclerViewAdapter(list)
            listItemsRecyclerView.layoutManager = LinearLayoutManager(activity)
        }
        return view
    }

    fun addTask(item: String) {
        list.tasks.add(item)
        val recyclerAdapter = listItemsRecyclerView.adapter as ListItemsRecyclerViewAdapter
        recyclerAdapter.list = list
        recyclerAdapter.notifyDataSetChanged()
    }

    companion object {
        fun newInstance(list: TaskList): ListDetailFragment {
            val fragment = ListDetailFragment()
            val args = Bundle()
            args.putParcelable(MainActivity.INTENT_LIST_KEY, list)
            fragment.arguments = args
            return fragment
        }

    }
}
