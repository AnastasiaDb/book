package com.example.books.controller;

import com.example.books.models.Book;
import com.example.books.repository.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/")
    public String getAllBooks(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "bookList";
    }

    @GetMapping("/book/{id}")
    public String getBookDetails(@PathVariable Long id, Model model) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book == null) {
            return "redirect:/";
        }
        model.addAttribute("book", book);
        return "bookDetails";
    }

    @GetMapping("/book/add")
    public String showAddCarForm(Model model) {
        model.addAttribute("book", new Book());
        return "addBookForm";
    }

    @PostMapping("/")
    public String addBook(@ModelAttribute Book book) {
        bookRepository.save(book);
        return "redirect:/";
    }

    @PostMapping("/book/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookRepository.deleteById(id);
        return "redirect:/";
    }
}
