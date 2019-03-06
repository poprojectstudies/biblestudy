package br.com.finpe.biblestudy;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import br.com.finpe.biblestudy.books.Book;
import br.com.finpe.biblestudy.books.BookAdapter;
import br.com.finpe.biblestudy.common.ListItemClickListener;
import br.com.finpe.biblestudy.service.BibleService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements ListItemClickListener<Book> {
    private BibleService bibleService = new BibleService();

    private ProgressBar pbLoadBooks;
    private RecyclerView bookListView;
    private BookAdapter bookAdapter;
    private ListItemClickListener<Book> listItemClickListener;
    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pbLoadBooks = findViewById(R.id.pb_load_books);
        bookListView = findViewById(R.id.rv_books);
        listItemClickListener = this;

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        bookListView.setLayoutManager(layoutManager);
        bookListView.setHasFixedSize(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final Context context = MainActivity.this;
        switch (item.getItemId()) {
            case R.id.action_search_book:
                String searchingBook = getResources().getString(R.string.search_book_started);
                setToastText(context, searchingBook);

                pbLoadBooks.setVisibility(View.VISIBLE);
                bibleService.getBooks(new Callback<List<Book>>() {
                    @Override
                    public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                        pbLoadBooks.setVisibility(View.INVISIBLE);
                        bookAdapter = new BookAdapter(response.body(), listItemClickListener);
                        bookListView.setAdapter(bookAdapter);
                        cancelToast();
                    }

                    @Override
                    public void onFailure(Call<List<Book>> call, Throwable t) {
                        pbLoadBooks.setVisibility(View.INVISIBLE);
                        String searchingBookFailed = getResources().getString(R.string.search_book_failed);
                        setToastText(context, searchingBookFailed);
                    }
                });
                break;
        }
        return true;
    }

    private void setToastText(Context context, String text) {
        cancelToast();
        toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        toast.show();
    }

    private void cancelToast() {
        if (toast != null) {
            toast.cancel();
        }
    }

    @Override
    public void onListItemClick(Book book) {
        Intent intent = new Intent(MainActivity.this, ContentActivity.class);
        intent.putExtra(Intent.EXTRA_TEXT, book.getAbbrev());
        intent.putExtra(Intent.EXTRA_INDEX, 1);
        startActivity(intent);
    }
}
