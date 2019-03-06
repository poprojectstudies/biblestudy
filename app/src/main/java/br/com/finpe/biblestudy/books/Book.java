package br.com.finpe.biblestudy.books;

import android.os.Parcel;
import android.os.Parcelable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Book implements Parcelable {
    private String abbrev;
    private String author;
    private int chapters;
    private String group;
    private String name;
    private String testament;

    protected Book(Parcel in) {
        abbrev = in.readString();
        author = in.readString();
        chapters = in.readInt();
        group = in.readString();
        name = in.readString();
        testament = in.readString();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(abbrev);
        dest.writeString(author);
        dest.writeInt(chapters);
        dest.writeString(group);
        dest.writeString(name);
        dest.writeString(testament);
    }
}
