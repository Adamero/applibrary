package pl.edu.wat.library.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import pl.edu.wat.library.R;
import pl.edu.wat.library.adapter.AuthorAdapter;
import pl.edu.wat.library.entity.Author;
import pl.edu.wat.library.retrofit.AuthorApi;
import pl.edu.wat.library.retrofit.RetrofitService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthorListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author_list);

        recyclerView = findViewById(R.id.authorsList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton floatingActionButton = findViewById(R.id.authorsList_fab);
        floatingActionButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, AuthorsForm.class);
            startActivity(intent);
        });

        loadAuthors();


    }

    private void loadAuthors() {
        RetrofitService retrofitService = new RetrofitService();
        AuthorApi authorApi = retrofitService.getRetrofit().create(AuthorApi.class);
        authorApi.getAllAuthor()
                .enqueue(new Callback<List<Author>>() {
                    @Override
                    public void onResponse(Call<List<Author>> call, Response<List<Author>> response) {
                        populateListView(response.body());

                    }

                    @Override
                    public void onFailure(Call<List<Author>> call, Throwable t) {
                        Toast.makeText(AuthorListActivity.this, "Failed to load Authors", Toast.LENGTH_SHORT).show();
                    }
                });


    }

    private void populateListView(List<Author> authorList) {
        AuthorAdapter authorAdapter = new AuthorAdapter(authorList);

        recyclerView.setAdapter(authorAdapter);

    }


}