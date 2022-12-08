package com.kitaplik.libraryservice.controller;

import com.kitaplik.libraryservice.dto.AddBookRequest;
import com.kitaplik.libraryservice.dto.LibraryDto;
import com.kitaplik.libraryservice.service.LibraryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/library")
public class LibraryController {

    private final LibraryService libraryService;

    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping("{id}")
    public ResponseEntity<LibraryDto> getLibraryById(@PathVariable  String id){
        return ResponseEntity.ok(libraryService.getAllBooksInLibraryById(id));
    }

    @PostMapping
    public ResponseEntity<LibraryDto> createLibrary(){
        return ResponseEntity.ok(libraryService.createLibrary());
    }


    @PutMapping
    public ResponseEntity<Void> addBookToLibrary(@RequestBody AddBookRequest addBookRequest){
       libraryService.addBookToLibrary(addBookRequest);
         return ResponseEntity.ok().build();
    }
}
