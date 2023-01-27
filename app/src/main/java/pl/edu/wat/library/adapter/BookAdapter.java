package pl.edu.wat.library.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import pl.edu.wat.library.R;
import pl.edu.wat.library.activities.DetailActivity;
import pl.edu.wat.library.entity.Book;

public class BookAdapter extends BaseAdapter {

    List<Book> books;

    Context context;
    TextView nameText;
    Button viewButton;

    public BookAdapter(List<Book> books, Context context) {
        this.books = books;
        this.context = context;
    }

    @Override
    public int getCount() {
        return books.size();
    }

    @Override
    public Object getItem(int position) {
        return books.get(position);
    }

    @Override
    public long getItemId(int position) {
        return Long.parseLong(books.get(position).getId());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.book_list, parent, false);
        }
        nameText = convertView.findViewById(R.id.nameText);
        nameText.setText(books.get(position).getTitle());
        viewButton = convertView.findViewById(R.id.viewButton);
        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callDetail(books.get(position).getId());
            }
        });
        return convertView;
    }

    private void callDetail(String id) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra("id",id);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);


    }
}
