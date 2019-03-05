package br.com.finpe.biblestudy;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import br.com.finpe.biblestudy.dto.Book;
import br.com.finpe.biblestudy.dto.BookList;
import br.com.finpe.biblestudy.service.BibleService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private BibleService bibleService = new BibleService("90799bb5b996fddc-01");

    private TextView tvMainContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvMainContent = findViewById(R.id.tv_main_content);
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
                bibleService.getBooks(new Callback<BookList>() {
                    @Override
                    public void onResponse(Call<BookList> call, Response<BookList> response) {
                        StringBuilder books = new StringBuilder();

                        for (Book book:response.body().getData()) {
                            books.append(book.getName() + "\n\n\n\n");
                        }

                        tvMainContent.setText(books.toString());
                    }

                    @Override
                    public void onFailure(Call<BookList> call, Throwable t) {
                        String searchingBookFailed = getResources().getString(R.string.search_book_failed);
                        Toast.makeText(context, searchingBookFailed, Toast.LENGTH_SHORT).show();
                    }
                });
                break;
        }
        return true;
    }
}
