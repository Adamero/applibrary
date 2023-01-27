package pl.edu.wat.library.retrofit;

import java.util.List;

import pl.edu.wat.library.dto.BookRequest;
import pl.edu.wat.library.entity.Book;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface BookApi {

    @GET("/api/v1/books/show")
    Call<List<Book>> getAll();

    @GET("/api/v1/books/{id}")
    Call<Book> getOne(@Path("id") String id);

    @POST("/api/v1/books/add")
    Call<Book> create(@Body BookRequest bookRequest);

    @PUT("/api/v1/books/edit/{id}")
    Call<Book> edit(@Path("id") String id, @Body BookRequest bookRequest);

    @DELETE("/api/v1/books/del/{id}")
    Call<Void> delete(@Path("id") String id);

}
