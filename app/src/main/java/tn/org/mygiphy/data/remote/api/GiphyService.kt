package tn.org.mygiphy.data.remote.api

import retrofit2.http.GET
import retrofit2.http.Query
import tn.org.mygiphy.SEARCH_API
import tn.org.mygiphy.TRENDING_API
import tn.org.mygiphy.data.remote.model.GiphyModel

interface GiphyService {

    @GET(SEARCH_API)
    suspend fun searchGif(
        @Query("q") query: String,
        @Query("limit") limit: Int = 25,
        @Query("offset") offset: Int = 0,
        @Query("lang") lang: String = "en"
    ): GiphyModel

    @GET(TRENDING_API)
    suspend fun trendingGif(
        @Query("limit") limit: Int = 25,
    ): GiphyModel
}