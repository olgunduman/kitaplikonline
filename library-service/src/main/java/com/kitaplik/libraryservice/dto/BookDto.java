package com.kitaplik.libraryservice.dto;


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




}
