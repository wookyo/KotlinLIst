package com.v1.application.model

import com.google.gson.annotations.SerializedName

data class ResponseMainList(
    @SerializedName("total_count")
    val pageCount: Int?,
    @SerializedName("incomplete_results")
    val result: Boolean?,
    @SerializedName(value = "items")
    val itemModelList: ArrayList<MainListItem>,
    var title: String){

    data class MainListItem(
        @SerializedName("id")
        val id: String?,
        @SerializedName("avatar_url")
        val avatarUrl: String?,
        @SerializedName("login")
        val login: String?,
        @SerializedName("html_url")
        val htmlUrl: String?,
        var zzim:Boolean)
}
