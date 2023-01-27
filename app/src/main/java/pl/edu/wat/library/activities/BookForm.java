package pl.edu.wat.library.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.time.LocalDateTime;

import pl.edu.wat.library.MainActivity;
import pl.edu.wat.library.R;
import pl.edu.wat.library.dto.BookRequest;
import pl.edu.wat.library.entity.Book;
import pl.edu.wat.library.retrofit.AuthorApi;
import pl.edu.wat.library.retrofit.BookApi;
import pl.edu.wat.library.retrofit.RetrofitService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookForm extends AppCompatActivity {

    EditText titleText;
    EditText descriptionText;
    EditText authorIdText;
    Button createButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_form);

        titleText = findViewById(R.id.nameTitleText);
        descriptionText = findViewById(R.id.nameDescriptionText);
        authorIdText = findViewById(R.id.nameAuthorIdText);
        createButton = findViewById(R.id.createButton);
        titleText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                createButton.setEnabled(buttonEnabled());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        descriptionText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                createButton.setEnabled(buttonEnabled());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        createButton.setEnabled(buttonEnabled());
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BookRequest bookRequest = new BookRequest(titleText.getText().toString(),descriptionText.getText().toString(),authorIdText.getText().toString(), LocalDateTime.now());
                create(bookRequest);
            }
        });
    }

    private void create(BookRequest bookRequest){
        RetrofitService retrofitService = new RetrofitService();
        BookApi bookApi = retrofitService.getRetrofit().create(BookApi.class);

        Call<Book> call = bookApi.create(bookRequest);
        call.enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                if(!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_LONG).show();
                    Log.e("Response err:", response.message());
                    return;
                }
                Book book = response.body();
                Toast.makeText(getApplicationContext(), book.getTitle() + " created !", Toast.LENGTH_LONG).show();
                callMain();
            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("Response err:", t.getMessage());
            }
        });
    }

    private void callMain() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    private boolean buttonEnabled(){
        return titleText.getText().toString().trim().length() > 0 && descriptionText.getText().toString().trim().length() > 0 && authorIdText.getText().toString().trim().length() > 0;
    }

}