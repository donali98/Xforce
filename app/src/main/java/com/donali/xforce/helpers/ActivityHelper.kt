package com.donali.xforce.helpers

import androidx.recyclerview.widget.RecyclerView

interface ActivityHelper {
    fun getLayoutManager():RecyclerView.LayoutManager
    fun showEmptySearchToast()
}