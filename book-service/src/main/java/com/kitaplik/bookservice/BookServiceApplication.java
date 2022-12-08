package com.kitaplik.bookservice;

import com.kitaplik.bookservice.model.Book;
import com.kitaplik.bookservice.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import java.util.List;

@SpringBootApplication
@EnableEurekaClient
public class BookServiceApplication implements CommandLineRunner {

	private final BookRepository bookRepository;

	public BookServiceApplication(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(BookServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Book book1 = Book.builder()
				.isbn("987654322")
				.bookYear(2021)
				.author("Ahmet")
				.title("Kitap 1")
				.pressName("Yayınevi 1")
				.build();
		Book book2 = Book.builder()
				.isbn("987654321")
				.bookYear(2021)
				.author("Mehmet")
				.title("Kitap 2")
				.pressName("Yayınevi 2")
				.build();
		Book book3 = Book.builder()
				.isbn("123456789")
				.bookYear(2021)
				.author("Ahmet")
				.title("Kitap 3")
				.pressName("Yayınevi 3")
				.build();

		List<Book> getAllBook = List.of(book1, book2, book3);

		List<Book> books = bookRepository.saveAll(getAllBook);
		System.out.println("Book saved" + books);

	}
}
