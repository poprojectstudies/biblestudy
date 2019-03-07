package br.com.finpe.biblestudy.contents;

import android.os.Parcel;
import android.os.Parcelable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContentBook implements Parcelable {
    private String abbrev;
    private String name;
    private String author;
    private String group;
    private String version;

    protected ContentBook(Parcel in) {
        abbrev = in.readString();
        name = in.readString();
        author = in.readString();
        group = in.readString();
        version = in.readString();
    }

    public static final Creator<ContentBook> CREATOR = new Creator<ContentBook>() {
        @Override
        public ContentBook createFromParcel(Parcel in) {
            return new ContentBook(in);
        }

        @Override
        public ContentBook[] newArray(int size) {
            return new ContentBook[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(abbrev);
        dest.writeString(name);
        dest.writeString(author);
        dest.writeString(group);
        dest.writeString(version);
    }
}
