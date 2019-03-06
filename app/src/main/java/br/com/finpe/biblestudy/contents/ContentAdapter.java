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
import br.com.finpe.biblestudy.common.ListItemClickListener;

public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.ContentViewHolder> {
    private final Content content;
    private List<Verse> verses;
    private ListItemClickListener<Content> listItemClickListener;

    public ContentAdapter(Content content, ListItemClickListener<Content> listItemClickListener) {
        this.content = content;
        this.verses = content.getVerses();
        this.listItemClickListener = listItemClickListener;
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

    class ContentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvVerseText;
        private Verse verse;

        public ContentViewHolder(@NonNull View itemView) {
            super(itemView);
            tvVerseText = itemView.findViewById(R.id.tv_verse_text);
            itemView.setOnClickListener(this);
        }

        public void bind(Verse verse) {
            this.verse = verse;
            tvVerseText.setText(verse.getText());
        }

        @Override
        public void onClick(View v) {
            content.setSelectedVerse(verse);
            listItemClickListener.onListItemClick(content);
        }
    }
}
