package com.juaracoding.simplelogin.Service;

import com.juaracoding.simplelogin.Model.Data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIInterfaceRest {
    @FormUrlEncoded

    @POST("login.php")
    Call<Data> requestLogin(@Field("username") String txtusername, @Field("password") String txtpassword);

//    @GET("datalist.php")
//    Call<List<Listt>> getListt(@Path("data") String Data);
}
