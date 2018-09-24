package com.sanjeev.er.amazedeliver.api_interface;

import com.sanjeev.er.amazedeliver.model.Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInetrface {

    @GET("/deliveries") // Working now without parameters
    Call<List<Model>> getDeliveriesList();
}
