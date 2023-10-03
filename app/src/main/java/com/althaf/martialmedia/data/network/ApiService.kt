package com.althaf.martialmedia.data.network

import com.althaf.martialmedia.data.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    // Nanti nanya lagi
    @GET("everything")
    fun getMMANews(
        @Query("q") keyWord: String = "UFC MMA PFL Bellator ",
        @Query("language") language: String = "en",
        @Query("pageSize") pageSize: Int = 50,
        ) : Call<NewsResponse>

    @GET("everything")
    fun getMuayThaiNews(
        @Query("q") keyWord: String = "muay thai",
        @Query("language") language: String = "en",
        @Query("pageSize") pageSize: Int = 50,
        ) : Call<NewsResponse>

    @GET("everything")
    fun getBoxingNews(
        @Query("q") keyWord: String = "boxing",
        @Query("pageSize") pageSize: Int = 50,
        ) : Call<NewsResponse>

    @GET("everything")
    fun searchNews(
        @Query("q") query: String,
        @Query("pageSize") pageSize: Int = 50
    ) : Call<NewsResponse>
}