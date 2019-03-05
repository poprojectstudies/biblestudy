package br.com.finpe.biblestudy;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import br.com.finpe.biblestudy.books.Book;
import br.com.finpe.biblestudy.books.BookAdapter;
import br.com.finpe.biblestudy.books.BookList;
import br.com.finpe.biblestudy.service.BibleService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private BibleService bibleService = new BibleService("90799bb5b996fddc-01");

    private ProgressBar pbLoadBooks;
    private RecyclerView bookList;
    private BookAdapter bookAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pbLoadBooks = findViewById(R.id.pb_load_books);
        bookList = findViewById(R.id.rv_books);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        bookList.setLayoutManager(layoutManager);
        bookList.setHasFixedSize(true);
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
                Toast.makeText(context, searchingBook, Toast.LENGTH_SHORT).show();

                pbLoadBooks.setVisibility(View.VISIBLE);
                bibleService.getBooks(new Callback<BookList>() {
                    @Override
                    public void onResponse(Call<BookList> call, Response<BookList> response) {
                        pbLoadBooks.setVisibility(View.INVISIBLE);
                        bookAdapter = new BookAdapter(response.body().getData());
                        bookList.setAdapter(bookAdapter);
                    }

                    @Override
                    public void onFailure(Call<BookList> call, Throwable t) {
                        pbLoadBooks.setVisibility(View.INVISIBLE);
                        String searchingBookFailed = getResources().getString(R.string.search_book_failed);
                        Toast.makeText(context, searchingBookFailed, Toast.LENGTH_SHORT).show();
                    }
                });
                break;
        }
        return true;
    }
}
