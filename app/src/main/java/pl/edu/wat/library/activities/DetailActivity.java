package pl.edu.wat.library.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import pl.edu.wat.library.MainActivity;
import pl.edu.wat.library.R;
import pl.edu.wat.library.entity.Book;
import pl.edu.wat.library.fragments.DeleteFragment;
import pl.edu.wat.library.retrofit.BookApi;
import pl.edu.wat.library.retrofit.DeleteInterface;
import pl.edu.wat.library.retrofit.RetrofitService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DetailActivity extends AppCompatActivity implements DeleteInterface {

    TextView idText;
    TextView titleText;
    TextView descriptionText;
    TextView dataText;
    Button editButton;
    Button deleteButton;
    Book book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        idText = findViewById(R.id.idText);
        titleText = findViewById(R.id.titleText);
        descriptionText = findViewById(R.id.descriptionText);
        dataText = findViewById(R.id.DataText);
        String id = getIntent().getExtras().getString("id");
        editButton = findViewById(R.id.editButton);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callEdit();
            }
        });
        deleteButton = findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDeleteDialog(book.getId());
            }
        });
        getOne(id);
    }

    private void getOne(String id) {
        RetrofitService retrofitService = new RetrofitService();
        BookApi bookApi = retrofitService.getRetrofit().create(BookApi.class);
        Call<Book> call = bookApi.getOne(id);
        call.enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                if(!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_LONG).show();
                    Log.e("Response err:", response.message());
                    return;
                }
                book = response.body();
                idText.setText(String.valueOf(book.getId()));
                titleText.setText(String.valueOf(book.getTitle()));
                descriptionText.setText(String.valueOf(book.getDescription()));
                dataText.setText(String.valueOf(book.getCreated()));
                System.out.println(book.getCreated());



            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("Response err:", t.getMessage());
            }
        });

    }

    private void callEdit() {
        Intent intent = new Intent(getApplicationContext(), EditActivity.class);
        intent.putExtra("book", book);
        startActivity(intent);

    }

    @Override
    public void showDeleteDialog(String id) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        DeleteFragment deleteFragment = new DeleteFragment("Delete book ", book.getId(), this);
        deleteFragment.show(fragmentManager, "Alert");
    }

    @Override
    public void delete(String id) {
        RetrofitService retrofitService = new RetrofitService();
        BookApi bookApi = retrofitService.getRetrofit().create(BookApi.class);
        Call<Void> call = bookApi.delete(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_LONG).show();
                    Log.e("Response err:", response.message());
                    return;
                }
                //book = response.body();
                Toast.makeText(getApplicationContext(), book.getTitle() + "deleted!!", Toast.LENGTH_LONG).show();
                callMain();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("Response err:", t.getMessage());
            }
        });
    }

    private void callMain(){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }


}