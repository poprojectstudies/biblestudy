package br.com.finpe.biblestudy.contents;

import android.os.Parcel;
import android.os.Parcelable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Verse implements Parcelable {
    private int number;
    private String text;

    protected Verse(Parcel in) {
        number = in.readInt();
        text = in.readString();
    }

    public static final Creator<Verse> CREATOR = new Creator<Verse>() {
        @Override
        public Verse createFromParcel(Parcel in) {
            return new Verse(in);
        }

        @Override
        public Verse[] newArray(int size) {
            return new Verse[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(number);
        dest.writeString(text);
    }
}
