package com.elysino.rxjava.metwork;

import com.elysino.rxjava.model.Food;

import java.util.List;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface MyWebServices {

    String BASE_URL="http://www.codingwithjks.tech/";

     retrofit2.Retrofit retrofit =new retrofit2.Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build();

    @GET("/food.php")
    Observable<List<Food>> getAllFoodList();

}
