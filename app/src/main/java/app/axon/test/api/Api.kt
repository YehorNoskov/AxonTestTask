package app.axon.test.api

import app.axon.test.api.response.UsersList
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("api/")
    fun getUsersList(@Query("results") results: Int, @Query("page") page: Int): Single<UsersList>

}