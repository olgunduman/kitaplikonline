package com.kitaplik.libraryservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AddBookRequest {
    private String id;
    private String isbn;

}
