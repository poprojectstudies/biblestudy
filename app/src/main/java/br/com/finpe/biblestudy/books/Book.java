package br.com.finpe.biblestudy.books;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Book {
    private String abbrev;
    private String author;
    private int chapters;
    private String group;
    private String name;
    private String testament;
}
