package com.sanjeev.er.amazedeliver.api_interface;

import android.content.Context;

import com.sanjeev.er.amazedeliver.MainActivity;
import com.sanjeev.er.amazedeliver.fragment.HomeFragment;
import com.sanjeev.er.amazedeliver.model.Constant;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
/* Author Sanjeev Sangral*/
public class API {
    private static Retrofit retrofit = null;
    public static APIInetrface getClient(Context context) {
        int cacheSize = 10 * 1024 * 1024; // 10 MB
        Cache cache = new Cache(context.getCacheDir(), cacheSize);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cache(cache)
                .build();
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constant.baseURL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        //Creating object for our interface
        APIInetrface api = retrofit.create(APIInetrface.class);
        return api; // return the APIInterface object
    }
}
