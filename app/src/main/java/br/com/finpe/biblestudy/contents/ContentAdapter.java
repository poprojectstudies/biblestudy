package br.com.finpe.biblestudy.contents;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.finpe.biblestudy.R;

public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.ContentViewHolder> {
    private List<Verse> verses;

    public ContentAdapter(List<Verse> verses) {
        this.verses = verses;
    }

    @NonNull
    @Override
    public ContentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.book_verse_item;
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layoutIdForListItem, viewGroup, false);
        ContentViewHolder viewHolder = new ContentViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ContentViewHolder contentViewHolder, int i) {
        contentViewHolder.bind(verses.get(i));
    }

    @Override
    public int getItemCount() {
        return verses.size();
    }

    class ContentViewHolder extends RecyclerView.ViewHolder {
        private TextView tvVerseText;

        public ContentViewHolder(@NonNull View itemView) {
            super(itemView);
            tvVerseText = itemView.findViewById(R.id.tv_verse_text);
        }

        public void bind(Verse verse) {
            tvVerseText.setText(verse.getText());
        }
    }
}
