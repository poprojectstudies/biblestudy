package br.com.finpe.biblestudy.books;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.finpe.biblestudy.R;
import br.com.finpe.biblestudy.common.ListItemClickListener;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {
    private List<Book> books;
    private final ListItemClickListener<Book> onClickListener;

    public BookAdapter(List<Book> books, ListItemClickListener<Book> onClickListener) {
        this.books = books;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.book_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layoutIdForListItem, viewGroup, false);
        BookViewHolder viewHolder = new BookViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder bookViewHolder, int i) {
        bookViewHolder.bind(books.get(i));
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    class BookViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvBookName;
        private Book book;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            tvBookName = itemView.findViewById(R.id.tv_book_name);
            itemView.setOnClickListener(this);
        }

        public void bind(Book book) {
            this.book = book;
            tvBookName.setText(book.getName());
        }

        @Override
        public void onClick(View itemView) {
            onClickListener.onListItemClick(book);
        }
    }
}
