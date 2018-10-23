package com.archana.WAssignment.rest;

import com.archana.WAssignment.model.StackOverflowResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ApiInterface {
    @GET("users?site=stackoverflow")
    Call<StackOverflowResponse> getUsers();
}
