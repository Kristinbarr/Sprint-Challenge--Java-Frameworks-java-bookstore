package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.models.Author;
import com.lambdaschool.starthere.models.Book;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {

    List<Book> findAll(Pageable pageable);

//    Book findById(long id);

    Book update(Book book, long id);

    void addWrote(long bookid,long authorid);

    void delete(long id);
}
