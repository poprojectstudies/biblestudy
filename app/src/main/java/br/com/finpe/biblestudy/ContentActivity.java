package br.com.finpe.biblestudy;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import br.com.finpe.biblestudy.contents.Content;
import br.com.finpe.biblestudy.contents.ContentAdapter;
import br.com.finpe.biblestudy.service.BibleService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContentActivity extends AppCompatActivity {
    private BibleService bibleService = new BibleService();

    private ProgressBar pbLoadContent;
    private RecyclerView contentView;
    private ContentAdapter contentAdapter;
    private TextView tvContentName;
    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Context context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        pbLoadContent = findViewById(R.id.pb_load_content);
        contentView = findViewById(R.id.rv_content);
        tvContentName = findViewById(R.id.tv_content_name);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        contentView.setLayoutManager(layoutManager);

        pbLoadContent.setVisibility(View.VISIBLE);
        bibleService.getContent("gn", 1, new Callback<Content>() {
            @Override
            public void onResponse(Call<Content> call, Response<Content> response) {
                pbLoadContent.setVisibility(View.INVISIBLE);
                Content content = response.body();
                contentAdapter = new ContentAdapter(content.getVerses());
                contentView.setAdapter(contentAdapter);
                tvContentName.setText(String.format("%s - %s", content.getBook().getName(), content.getChapter().getNumber()));
            }

            @Override
            public void onFailure(Call<Content> call, Throwable t) {
                pbLoadContent.setVisibility(View.INVISIBLE);
                String searchingBookFailed = getResources().getString(R.string.search_verses_failed);
                setToastText(context, searchingBookFailed);

            }
        });
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
}
