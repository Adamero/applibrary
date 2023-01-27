package pl.edu.wat.library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import pl.edu.wat.library.activities.BookForm;
import pl.edu.wat.library.adapter.BookAdapter;
import pl.edu.wat.library.entity.Author;
import pl.edu.wat.library.entity.Book;
import pl.edu.wat.library.retrofit.AuthorApi;
import pl.edu.wat.library.retrofit.BookApi;
import pl.edu.wat.library.retrofit.RetrofitService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    List<Book> book;
    ListView listView;
    FloatingActionButton createButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        listView = findViewById(R.id.listView);
        createButton = findViewById(R.id.createButton);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callCreate();
            }
        });
        getAll();
    }

    private void getAll() {
        RetrofitService retrofitService = new RetrofitService();
        BookApi bookApi = retrofitService.getRetrofit().create(BookApi.class);
        listView = findViewById(R.id.listView);
        Call<List<Book>> call = bookApi.getAll();

        call.enqueue(new Callback<List<Book>>() {
                    @Override
                    public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                        if(!response.isSuccessful()){
                            Log.e("Response err: ", response.message());
                            return;
                        }
                        book = response.body();
                        BookAdapter bookAdapter = new BookAdapter(book, getApplicationContext());
                        listView.setAdapter(bookAdapter);
                        book.forEach(p -> Log.i("Books: ",p.toString()));
                    }

                    @Override
                    public void onFailure(Call<List<Book>> call, Throwable t) {
                        Toast.makeText(MainActivity.this,"Failed to load Authors",Toast.LENGTH_SHORT).show();
                        System.out.println(t);
                    }
                });

    }

    private void callCreate() {
        Intent intent = new Intent(getApplicationContext(), BookForm.class);
        startActivity(intent);
    }

}
