package pl.edu.wat.library.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import pl.edu.wat.library.R;

public class AuthorHolder extends RecyclerView.ViewHolder {

    TextView id, firstname, lastname;

    public AuthorHolder(@NonNull View itemView) {
        super(itemView);
        id = itemView.findViewById(R.id.authorsListItem_id);
        firstname = itemView.findViewById(R.id.authorsListItem_firstname);
        lastname = itemView.findViewById(R.id.authorsListItem_lastname);
    }
}
