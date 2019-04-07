package app.axon.test.api;

import app.axon.test.api.response.UsersList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    @GET("api/")
    Call<UsersList> getUsersList(@Query("results") int results, @Query("page") int page);
}
