package com.lambdaschool.starthere.controllers;


import com.lambdaschool.starthere.models.Book;
import com.lambdaschool.starthere.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BooksController {

    @Autowired
    private BookService bookService;


    @GetMapping(value = "/books", produces = {"application/json"})
    public ResponseEntity<?> listAllBooks() {
        List<Book> myBooks = bookService.findAll();
        return new ResponseEntity<>(myBooks, HttpStatus.OK);
    }

    
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping(value = "/data/books/{id}")
    public ResponseEntity<?> updateBook(@RequestBody Book updateBook, @PathVariable long id)
    {
        bookService.update(updateBook, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/data/books/{id}")
    public ResponseEntity<?> deleteBookById(
            @PathVariable
                    long id)
    {
        bookService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
