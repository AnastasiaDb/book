
package com.example.books.repository;

import com.example.books.models.Book;
import com.example.books.repository.BookRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

@SpringBootApplication
public class BookRepository {

    public static void main(String[] args) {
        SpringApplication.run(BookRepository.class, args);
    }

    @Bean
    public ApplicationRunner init(BookRepository bookRepository) {
        return args -> {
            Book book = new Book();
            book.setWriter("Дубинская Анастасия");
            book.setDate("1924.01.01");
            book.setTitle("Я и ты да мы с тобой");
            bookRepository.save(book);

            System.out.println("Book saved: " + bookRepository.count());
        };
    }

}
