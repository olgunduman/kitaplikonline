package com.kitaplik.libraryservice.exception;

public class LibraryNotFoundException extends RuntimeException {
    public LibraryNotFoundException(String s) {
        super(s);
    }
}
