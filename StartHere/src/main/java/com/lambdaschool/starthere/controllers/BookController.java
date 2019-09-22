package com.lambdaschool.starthere.controllers;


import com.lambdaschool.starthere.models.Book;
import com.lambdaschool.starthere.models.ErrorDetail;
import com.lambdaschool.starthere.services.BookService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    // GET localhost:2019/books
    @ApiOperation(value = "returns all books", response = Book.class, responseContainer = "List")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                    value = "Results page number"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Number of records per page"),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "integer",
                    paramType = "query", value = "Sorting criteria in the format: property(asc|desc)" +
                    "Default sort order is ascending " +
                    "Multiple sort criteria are supported)")})
    // GET http://localhost:2019/books/?page=0&size=3
    @GetMapping(value = "/books", produces = {"application/json"})
    public ResponseEntity<?> listAllBooks(@PageableDefault(page = 0, size = 5) Pageable pageable) {
        List<Book> myBooks = bookService.findAll(pageable);
        return new ResponseEntity<>(myBooks, HttpStatus.OK);
    }


    // PUT localhost:2019/data/books/{id}
    @ApiOperation(value = "returns book by given id", response = Book.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Book Found", response = Book.class),
            @ApiResponse(code = 204, message = "Server found no content", response = Book.class),
            @ApiResponse(code = 404, message = "Book Not Found", response = ErrorDetail.class)
    })
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping(value = "/data/books/{id}")
    public ResponseEntity<?> updateBook(
            @RequestBody Book updateBook,
            @ApiParam(value = "Book Id", required = true, example = "1")
            @PathVariable long id) {
        bookService.update(updateBook, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // POST localhost:2019/data/books/{bookid}/authors/{authorid}
    @ApiOperation(value = "assigns an author to a book", response = Book.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "bookid and authorid asigned to each other", response = void.class),
            @ApiResponse(code = 404, message = "id not found", response = ErrorDetail.class)
    })
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping(value = "/data/books/{bookid}/authors/{authorid}")
    public ResponseEntity<?> addBookAuthor(
            HttpServletRequest request,
            @ApiParam(value = "Book Id", required = true, example = "1")
            @PathVariable long bookid,
            @ApiParam(value = "Author Id", required = true, example = "1")
            @PathVariable long authorid) throws URISyntaxException {
        bookService.addWrote(bookid, authorid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // DELETE localhost:2019/data/books/{id}
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Book Deleted", response = Book.class),
            @ApiResponse(code = 404, message = "Book Not Found", response = ErrorDetail.class)
    })
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/data/books/{id}")
    public ResponseEntity<?> deleteBookById(
            @ApiParam(value = "Author Id", required = true, example = "1")
            @PathVariable long id) {
        bookService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
