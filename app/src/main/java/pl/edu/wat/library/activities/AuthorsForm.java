package pl.edu.wat.library.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.logging.Level;
import java.util.logging.Logger;

import pl.edu.wat.library.R;
import pl.edu.wat.library.entity.Author;
import pl.edu.wat.library.retrofit.AuthorApi;
import pl.edu.wat.library.retrofit.RetrofitService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthorsForm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authors_form);

        initializeComponents();
    }


    private void initializeComponents() {
        TextInputEditText inputEditName = findViewById(R.id.form_textFieldName);
        TextInputEditText inputEditSurname = findViewById(R.id.form_textFieldSurname);

        MaterialButton buttonSave = findViewById(R.id.form_buttonSave);

        RetrofitService retrofitService = new RetrofitService();
        AuthorApi authorApi = retrofitService.getRetrofit().create(AuthorApi.class);

        buttonSave.setOnClickListener(view -> {
            String firstName = String.valueOf(inputEditName.getText());
            String lastName = String.valueOf(inputEditSurname.getText());


            Author author = new Author();
            author.setFirstName(firstName);
            author.setLastName(lastName);
            authorApi.save(author)
                    .enqueue(new Callback<Author>() {
                        @Override
                        public void onResponse(Call<Author> call, Response<Author> response) {
                            Toast.makeText(AuthorsForm.this, "Save successful!", Toast.LENGTH_SHORT).show();
                            callMain();
                        }

                        @Override
                        public void onFailure(Call<Author> call, Throwable t) {
                            Toast.makeText(AuthorsForm.this, "Save failed!!!", Toast.LENGTH_SHORT).show();
                            Logger.getLogger(AuthorsForm.class.getName()).log(Level.SEVERE, "Error occured", t);
                        }
                    });
        });
    }

    private void callMain() {
        Intent intent = new Intent(getApplicationContext(), AuthorListActivity.class);
        startActivity(intent);
    }
}

