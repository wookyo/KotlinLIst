package com.v1.application.view.main

import com.v1.application.model.ResponseMainList.MainListItem

interface MainListCallback {
    fun onClick (item: MainListItem)

    fun onClickZzim(item: MainListItem)

    fun onClickLink(url: String)
}