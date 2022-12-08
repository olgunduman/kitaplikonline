package com.kitaplik.libraryservice.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LibraryDto {

    private String id;
    private List<BookDto> userBookList;
}
