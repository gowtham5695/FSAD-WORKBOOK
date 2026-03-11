package com.klu.controller;

import com.klu.model.Book;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class LibraryController {

    // In-memory list to store books
    private List<Book> bookList = new ArrayList<>();

    // 1. Welcome message
    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to Online Library System ";
    }

    // 2. Total books count
    @GetMapping("/count")
    public int getTotalBooks() {
        return bookList.size();
    }

    // 3. Sample price
    @GetMapping("/price")
    public double getSamplePrice() {
        return 499.99;
    }

    // 4. List of book titles
    @GetMapping("/books")
    public List<String> getBookTitles() {
        List<String> titles = new ArrayList<>();
        for (Book b : bookList) {
            titles.add(b.getTitle());
        }
        return titles;
    }

    // 5. Get book by ID
    @GetMapping("/books/{id}")
    public Object getBookById(@PathVariable int id) {
        for (Book b : bookList) {
            if (b.getId() == id) {
                return b;
            }
        }
        return "Book not found";
    }

    // 6. Search book
    @GetMapping("/search")
    public String searchBook(@RequestParam String title) {
        return "You searched for book: " + title;
    }

    // 7. Author
    @GetMapping("/author/{name}")
    public String getAuthor(@PathVariable String name) {
        return "Books written by author: " + name;
    }

    // 8. Add book
    @PostMapping("/addbook")
    public String addBook(@RequestBody Book book) {
        bookList.add(book);
        return "Book added successfully: " + book.getTitle();
    }

    // 9. View all books
    @GetMapping("/viewbooks")
    public List<Book> viewBooks() {
        return bookList;
    }
}

