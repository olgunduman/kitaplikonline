package com.kitaplik.bookservice.dto;

import com.kitaplik.bookservice.model.Book;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDto {

    private BookIdDto id= null;
    private String title;
    private int bookYear;
    private String author;
    private String pressName;
    private String isbn;



    public static BookDto convert(Book book){
       return BookDto.builder()
                .id(BookIdDto.builder().id(book.getId()).isbn(book.getIsbn()).build())
                .title(book.getTitle())
                .bookYear(book.getBookYear())
                .author(book.getAuthor())
                .pressName(book.getPressName())
                .isbn(book.getIsbn())
                .build();

    }
}
