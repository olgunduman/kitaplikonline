package com.kitaplik.libraryservice.service;

import com.kitaplik.libraryservice.client.BookServiceClient;
import com.kitaplik.libraryservice.dto.AddBookRequest;
import com.kitaplik.libraryservice.dto.LibraryDto;
import com.kitaplik.libraryservice.exception.LibraryNotFoundException;
import com.kitaplik.libraryservice.model.Library;
import com.kitaplik.libraryservice.repository.LibraryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class LibraryService {

    private final LibraryRepository libraryRepository;
    private final BookServiceClient bookServiceClient;

    public LibraryService(LibraryRepository libraryRepository, BookServiceClient bookServiceClient) {
        this.libraryRepository = libraryRepository;
        this.bookServiceClient = bookServiceClient;
    }

    public LibraryDto getAllBooksInLibraryById(String id){
        Library library = libraryRepository.findById(id).orElseThrow(() -> new LibraryNotFoundException("Library could not found by id : " + id));

        LibraryDto libraryDto = new LibraryDto(library.getId(),
                library.getUserBook().stream()
                        .map(bookServiceClient::getBookById) //feign client
                        .map(ResponseEntity::getBody)
                        .toList());

        return libraryDto;
    }

    public LibraryDto createLibrary(){
        Library newLibrary = libraryRepository.save(new Library());

        return LibraryDto.builder()
                .id(newLibrary.getId())
                .build();
    }

    public void addBookToLibrary(AddBookRequest request){
        String bookId = bookServiceClient.getBookByIsbn(request.getIsbn()).getBody().getId();

        Library library = libraryRepository.findById(request.getId())
                .orElseThrow(() -> new LibraryNotFoundException("Library could not found by id : " + request.getId()));

        library.getUserBook().add(bookId);

        libraryRepository.save(library);

    }
}
