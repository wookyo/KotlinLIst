package com.v1.application.repository

import androidx.lifecycle.MutableLiveData
import com.v1.application.model.ResponseMainList
import com.v1.application.repository.retrofit.RetrofitClient
import com.v1.application.utils.LogUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainListRepository {
    private val TAG: String = MainListRepository::class.java.simpleName
    private val main_list = MutableLiveData<ResponseMainList>()
    private val mainListLiveData: MutableLiveData<ResponseMainList?> = MutableLiveData()

    fun getMainlistCall(): MutableLiveData<ResponseMainList> {
        val call = RetrofitClient.apiInterface.getMainList()
        call.enqueue(object: Callback<ResponseMainList> {
            override fun onFailure(call: Call<ResponseMainList>, t: Throwable) {
                LogUtils.e(TAG, t.message.toString())
            }

            override fun onResponse(call: Call<ResponseMainList>, response: Response<ResponseMainList>) {
                LogUtils.e(TAG, response.body().toString())
                val data = response.body()
                data?.let { data ->
                    main_list.value = data
                }
            }
        })
        return main_list
    }

    fun requestMainListData(){
        val call = RetrofitClient.apiInterface.getMainList()
        call.enqueue(object: Callback<ResponseMainList> {
            override fun onFailure(call: Call<ResponseMainList?>,
                                   t: Throwable) {
                LogUtils.e(TAG, t.message.toString())
                mainListLiveData.value = null
            }

            override fun onResponse(call: Call<ResponseMainList?>,
                                    response: Response<ResponseMainList?>) {
                LogUtils.e(TAG, response.body().toString())
                val data = response.body()
                data?.let { data ->
                    data.apply {
                        // api 파라메터가 없어 임의로 추가
                        title = "SHOP"
                    }
                    mainListLiveData.value = data
                }
            }
        })
    }

     fun getMainListLiveData() :MutableLiveData<ResponseMainList?>{
        return this.mainListLiveData
    }
}