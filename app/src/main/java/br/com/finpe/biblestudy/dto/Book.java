package br.com.finpe.biblestudy.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Book {
    private String id;
    private String bibleId;
    private String abbreviation;
    private String name;
    private String nameLong;
}
