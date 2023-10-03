package com.althaf.martialmedia

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.althaf.martialmedia.data.NewsResponse
import com.althaf.martialmedia.data.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel: ViewModel() {

    private val _mmaNews = MutableLiveData<NewsResponse>()
    val mmaNews get() = _mmaNews as LiveData<NewsResponse>

    private val _muayThaiNews = MutableLiveData<NewsResponse>()
    val muayThaiNews get() = _muayThaiNews as LiveData<NewsResponse>

    private val _boxingNews = MutableLiveData<NewsResponse>()
    val boxingNews get() = _boxingNews as LiveData<NewsResponse>

    private var _searchNews = MutableLiveData<NewsResponse>()
    val searchNews get() = _searchNews as LiveData<NewsResponse>

    fun getMMANews() {
        ApiClient.getApiService().getMMANews().enqueue(object : Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if (response.isSuccessful) {
                    _mmaNews.postValue(response.body())
                } else Log.e(
                    "NewsViewModel",
                    "onFailedCall: Call error Http Status Code :" + response.code()
                )
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Log.e("NewsViewModel", "onFailure: " + t.localizedMessage )
            }

        })
    }

    fun getStrikingNews() {
        ApiClient.getApiService().getMuayThaiNews().enqueue(object : Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if (response.isSuccessful) {
                    _muayThaiNews.postValue(response.body())
                } else Log.e(
                    "NewsViewModel",
                    "onFailedCall: Call error Http Status Code :" + response.code()
                )
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Log.e("NewsViewModel", "onFailure: " + t.localizedMessage )
            }

        })
    }

    fun getGrapplingNews() {
        ApiClient.getApiService().getBoxingNews().enqueue(object : Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if (response.isSuccessful) {
                    _boxingNews.postValue(response.body())
                } else Log.e(
                    "NewsViewModel",
                    "onFailedCall: Call error Http Status Code :" + response.code()
                )
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Log.e("NewsViewModel", "onFailure: " + t.localizedMessage )
            }

        })
    }

    fun getSearchNews(query: String) {
        ApiClient.getApiService().searchNews(query).enqueue(object : Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if (response.isSuccessful) {
                    _searchNews.postValue(response.body())
                } else Log.e("NewsViewModel", "OnResponse: ${response.message()}")
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Log.e("NewsViewModel", "onFailure: " + t.localizedMessage )
            }

        })
    }
}