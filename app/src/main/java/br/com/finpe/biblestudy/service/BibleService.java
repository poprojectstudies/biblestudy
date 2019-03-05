package br.com.finpe.biblestudy.service;

import java.util.List;

import br.com.finpe.biblestudy.books.Book;
import br.com.finpe.biblestudy.contents.Content;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BibleService {
    private final String BASE_URL = "https://bibleapi.co/api/";

    public void getBooks(Callback<List<Book>> callback) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ScriptureApi service = retrofit.create(ScriptureApi.class);

        Call<List<Book>> bookList = service.listBooks();

        bookList.enqueue(callback);
    }

    public void getContent(String bookId, int chapter, Callback<Content> callback) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ScriptureApi service = retrofit.create(ScriptureApi.class);

        Call<Content> content = service.listContent(bookId, chapter);

        content.enqueue(callback);
    }
}
