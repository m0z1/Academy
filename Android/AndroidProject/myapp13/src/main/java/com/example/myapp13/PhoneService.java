package com.example.myapp13;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface PhoneService {
    @POST("insert")
    Call<Phone> save(@Body Phone phone);

    @GET("find")
    Call<List<Phone>> findAll();

    @DELETE("delete/{id}")
    Call<Void> deleteByid(@Path("id") Long id);

    @PUT("update/{id}")
    Call<Phone> update(@Path("id") Long id, @Body Phone phone);

}
