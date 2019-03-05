package br.com.finpe.biblestudy.service;

import java.util.List;

import br.com.finpe.biblestudy.books.Book;
import br.com.finpe.biblestudy.contents.Content;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ScriptureApi {
    @GET("books")
    Call<List<Book>> listBooks();

    @GET("verses/nvi/{bookId}/{chapter}")
    Call<Content> listContent(@Path("bookId") String bookId, @Path("chapter") int chapter);
}
