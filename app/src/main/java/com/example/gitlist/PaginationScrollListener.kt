package com.example.gitlist

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

/**
 * Pagination class to add more items to the list when reach the last item.
 */
abstract class PaginationScrollListener : RecyclerView.OnScrollListener() {

    //abstract fun mPreviousTotal(): Int

    //abstract fun isLoading(): Boolean
    var mPreviousTotal = 0;
    var mLoading = true;

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        if (checkRefresh()) { mPreviousTotal = 0 }
        
        val visibleItemCount = recyclerView.childCount
        val totalItemCount = recyclerView.layoutManager?.itemCount
        val firstVisibleItemPosition = (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

        if (mLoading) {
            if (totalItemCount != null && totalItemCount > mPreviousTotal) {
                mLoading = false
                mPreviousTotal = totalItemCount
            }
        }

        val visibleTreshold = 10

        if (!mLoading && totalItemCount != null &&  (totalItemCount - visibleItemCount) <= (firstVisibleItemPosition + visibleTreshold)) {
            //this is the end... you know...
            loadMoreItems()
            mLoading = true
        }

    }


    abstract fun loadMoreItems()

    abstract fun checkRefresh(): Boolean
}