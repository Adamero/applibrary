package pl.edu.wat.library.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pl.edu.wat.library.R;
import pl.edu.wat.library.entity.Author;

public class AuthorAdapter extends RecyclerView.Adapter<AuthorHolder> {

    private List<Author> authorList;

    public AuthorAdapter(List<Author> authorList) {
        this.authorList = authorList;
    }

    @NonNull
    @Override
    public AuthorHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_authors_item, parent, false);
        return new AuthorHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AuthorHolder holder, int position) {
        Author author = authorList.get(position);
        holder.id.setText(author.getId());
        holder.firstname.setText(author.getFirstName());
        holder.lastname.setText(author.getLastName());
    }

    @Override
    public int getItemCount() {
        return authorList.size();
    }
}
