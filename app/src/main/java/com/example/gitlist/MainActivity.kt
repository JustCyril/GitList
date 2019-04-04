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

    var ENTER_COUNTER = 0 //counting times of entering into getFakeData()
    val itemsCount = 0
    var itemsOffset = itemsCount
    var items = getFakeData()
    var ifRefreshed = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startInit()

        mRecyclerView = findViewById(R.id.mRecyclerView)
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mRecViewAdapter = RecyclerViewAdapter(items)
        mRecyclerView.adapter = mRecViewAdapter

        swipeRefresh = findViewById(R.id.swipeRefresh)
        swipeRefresh.setOnRefreshListener { onRefresh() }

        mRecyclerView.addOnScrollListener(object : PaginationScrollListener() {

            override fun loadMoreItems() {
                itemsOffset += 10
                //you have to call loadmore items to get more data
                getMoreItems()
            }

            override fun checkRefresh(): Boolean {
                    if (ifRefreshed) {
                        ifRefreshed = false
                        return true
                    } else {
                        return ifRefreshed
                    }

            }

        })

    }

    fun startInit() {
        ENTER_COUNTER = 0 //counting times of entering into getFakeData()
        itemsOffset = itemsCount
        items = getFakeData()
    }

    fun getFakeData(): ArrayList<PostItem> {
        val localItems = ArrayList<PostItem>()
        ENTER_COUNTER++

        var item = PostItem()

        for (i in 1..10) {
            item = PostItem("description", "time", "title")
            item.description += " enter №$ENTER_COUNTER card №$i"
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

        mRecViewAdapter.addData(getFakeData())

    }

    fun onRefresh() {
        startInit()
        ifRefreshed = true
        mRecViewAdapter.setData(items)
        swipeRefresh.isRefreshing = false
    }

}
