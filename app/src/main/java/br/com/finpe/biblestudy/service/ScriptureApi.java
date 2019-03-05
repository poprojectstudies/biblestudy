package br.com.finpe.biblestudy.service;

import br.com.finpe.biblestudy.dto.BookList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface ScriptureApi {
    @Headers("api-key: 1cff645d96a0979662467916496e67c4")
    @GET("{bibleId}/books")
    Call<BookList> listBooks(@Path("bibleId") String bibleId);
}
