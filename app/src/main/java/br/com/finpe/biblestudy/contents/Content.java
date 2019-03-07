package br.com.finpe.biblestudy.contents;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Content implements Parcelable {
    private ContentBook book;
    private Chapter chapter;
    private List<Verse> verses;
    private Verse selectedVerse;

    protected Content(Parcel in) {
        book = in.readParcelable(ContentBook.class.getClassLoader());
        chapter = in.readParcelable(Chapter.class.getClassLoader());
        verses = in.createTypedArrayList(Verse.CREATOR);
        selectedVerse = in.readParcelable(Verse.class.getClassLoader());
    }

    public static final Creator<Content> CREATOR = new Creator<Content>() {
        @Override
        public Content createFromParcel(Parcel in) {
            return new Content(in);
        }

        @Override
        public Content[] newArray(int size) {
            return new Content[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(book, flags);
        dest.writeParcelable(chapter, flags);
        dest.writeTypedList(verses);
        dest.writeParcelable(selectedVerse, flags);
    }
}
