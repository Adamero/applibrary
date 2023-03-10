package pl.edu.wat.library.retrofit;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

    private Retrofit retrofit;

    public RetrofitService() {
        initializeRetrofit();
    }

    private void initializeRetrofit() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://172.23.244.7:8080")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }


    public Retrofit getRetrofit() {
        return retrofit;
    }

}
