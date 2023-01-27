package pl.edu.wat.library.retrofit;

import java.util.List;

import pl.edu.wat.library.entity.Author;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface AuthorApi {

    @GET("api/v1/author/show")
    Call<List<Author>> getAllAuthor();

    @Headers("Content-Type: application/json")
    @POST("api/v1/author/add")
    Call<Author> save(@Body Author author);


}
