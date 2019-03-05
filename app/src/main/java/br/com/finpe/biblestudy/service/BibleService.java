package br.com.finpe.biblestudy.service;

import br.com.finpe.biblestudy.dto.BookList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BibleService {
    private final String BASE_URL = "https://api.scripture.api.bible/v1/bibles/";
    private final String bibleId;

    public BibleService(String bibleId) {
        this.bibleId = bibleId;
    }

    public void getBooks(Callback<BookList> callback) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ScriptureApi service = retrofit.create(ScriptureApi.class);

        Call<BookList> bookList = service.listBooks(bibleId);

        bookList.enqueue(callback);
    }
}
