package br.com.finpe.biblestudy.contents;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Content {
    private ContentBook book;
    private Chapter chapter;
    private List<Verse> verses;
    private Verse selectedVerse;
}
