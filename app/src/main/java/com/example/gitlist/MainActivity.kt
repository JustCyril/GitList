package com.example.gitlist

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    lateinit var mRecyclerView: RecyclerView
    lateinit var swipeRefresh: SwipeRefreshLayout
    lateinit var mRecViewAdapter: RecyclerViewAdapter
    var isLastPage: Boolean = false
    var isLoading: Boolean = false
    var ENTER_COUNTER = 0
    val PAGE_START = 1
    var currentPage = PAGE_START
    var items = getFakeData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mRecyclerView = findViewById(R.id.mRecyclerView)
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mRecViewAdapter = RecyclerViewAdapter(items)
        mRecyclerView.adapter = mRecViewAdapter

        swipeRefresh = findViewById(R.id.swipeRefresh)
        swipeRefresh.setOnRefreshListener { onRefresh() }

        mRecyclerView.addOnScrollListener(object : PaginationScrollListener(LinearLayoutManager(this)) {
            override fun isLastPage(): Boolean {
                return isLastPage
            }

            override fun isLoading(): Boolean {
                return isLoading
            }

            override fun loadMoreItems() {
                isLoading = true
                currentPage++
                //you have to call loadmore items to get more data
                getMoreItems()
            }
        })

    }

    fun getFakeData(): ArrayList<PostItem> {
        val localItems = ArrayList<PostItem>()
        ENTER_COUNTER++

        var item = PostItem("des", "time", "title")

        for (i in 1..10) {
            item = PostItem("des", "time", "title")
            item.description += " enter №$ENTER_COUNTER & $i"
            item.time += "$i"
            item.title += "$i"
            localItems.add(item)
        }

        return localItems
    }


    fun getMoreItems() {
        //after fetching your data assuming you have fetched list in your
        // recyclerview adapter assuming your recyclerview adapter is
        //rvAdapter
        //after getting your data you have to assign false to isLoading
        isLoading = false

        mRecViewAdapter.addData(getFakeData())

    }

    fun onRefresh() {
        //ENTER_COUNTER = 0
        currentPage = PAGE_START;
        isLastPage = false
        mRecViewAdapter.setData(getFakeData())
        swipeRefresh.isRefreshing = false
    }

}
