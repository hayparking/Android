package com.example.taskplanner.Service;



import com.example.taskplanner.Model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserService {

    @GET("/api/v1/user/{id}")
    Call<User> getUserByEmail(@Path("id") String email);

    @POST("/api/v1/user")
    Call<User> createUser(@Body User user);

}
