package com.v1.application.repository.retrofit

import com.v1.application.constant.APIConstant
import com.v1.application.model.ResponseMainList
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitApiInterface {
    @GET(APIConstant.BASE_URL_MAIN_LIST_SEARCH)
    fun getMainList() : Call<ResponseMainList>

}