package com.kitaplik.libraryservice.client;

import com.kitaplik.libraryservice.dto.BookDto;
import com.kitaplik.libraryservice.dto.BookIdDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "book-service",path = "/v1/book")
public interface BookServiceClient {

     Logger logger = LoggerFactory.getLogger(BookServiceClient.class);
    @GetMapping("/isbn/{isbn}")
    @CircuitBreaker(name = "getBookByIsbnCircuitBreaker", fallbackMethod = "getBookFallback")
     ResponseEntity<BookIdDto> getBookByIsbn(@PathVariable(value = "isbn") String isbn);

    default ResponseEntity<BookIdDto> getBookFallback(String isbn, Exception exception){
            logger.info("Book not found by isbn : " + isbn + "returning default BookDto Object");
        return ResponseEntity.ok(new BookIdDto("default-book","default-isbn"));
    }


    @GetMapping("/book/{id}")
    @CircuitBreaker(name = "getBookByIdCircuitBreaker", fallbackMethod = "getBookByIdFallback")
     ResponseEntity<BookDto> getBookById(@PathVariable(value = "id")  String id);

    default ResponseEntity<BookDto> getBookByIdFallback(String id, Exception exception){
        logger.info("Book not found by id : " + id + "returning default BookDto Object");
        return ResponseEntity.ok(BookDto.builder()
                .id(BookIdDto.builder().id("default-book").isbn("default-isbn").build())
                .build());
}

    }
