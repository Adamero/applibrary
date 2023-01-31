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

import pl.edu.wat.library.R;
import pl.edu.wat.library.dto.BookRequest;
import pl.edu.wat.library.entity.Book;
import pl.edu.wat.library.retrofit.BookApi;
import pl.edu.wat.library.retrofit.RetrofitService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditActivity extends AppCompatActivity {

    Book book;
    EditText editTitle;
    EditText editDescription;
    EditText editAuthorId;
    Button editButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Intent detailIntent = getIntent();
        book = (Book) detailIntent.getSerializableExtra("book");
        //Log.i("book", book.toString());
        editTitle = findViewById(R.id.nameTitleText);
        editDescription = findViewById(R.id.nameDescriptionText);
        editAuthorId = findViewById(R.id.nameAuthorIdText);

        editTitle.setText(book.getTitle());
        editDescription.setText(book.getDescription());
        editAuthorId.setText(String.valueOf(book.getAuthor()));
        editButton = findViewById(R.id.editButton);

        editTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                editButton.setEnabled(buttonEnabled());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        editDescription.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                editButton.setEnabled(buttonEnabled());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        editButton.setEnabled(buttonEnabled());

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BookRequest bookRequest = new BookRequest(editTitle.getText().toString(), editDescription.getText().toString(), editAuthorId.getText().toString(), null);
                edit(bookRequest);
            }
        });
    }

    private void edit(BookRequest bookRequest) {
        RetrofitService retrofitService = new RetrofitService();
        BookApi bookApi = retrofitService.getRetrofit().create(BookApi.class);
        String id = book.getId();
        Call<Book> call = bookApi.edit(id, bookRequest);
        call.enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_LONG).show();
                    Log.e("Response err:", response.message());
                    return;
                }
                Book book = response.body();
                Toast.makeText(getApplicationContext(), book.getTitle() + " edited !", Toast.LENGTH_LONG).show();
                callMain();
            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {
                Toast.makeText(EditActivity.this, "Failed to load Authors", Toast.LENGTH_SHORT).show();
                System.out.println(t);
            }
        });
    }

    private void callMain() {
        Intent intent = new Intent(getApplicationContext(), BookList.class);
        startActivity(intent);
    }

    private boolean buttonEnabled() {
        return editTitle.getText().toString().trim().length() > 0 && editDescription.getText().toString().trim().length() > 0 && editAuthorId.getText().toString().trim().length() > 0;
    }


}