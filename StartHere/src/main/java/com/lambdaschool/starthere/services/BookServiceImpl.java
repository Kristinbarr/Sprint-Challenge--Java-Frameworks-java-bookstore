package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.exceptions.ResourceNotFoundException;
import com.lambdaschool.starthere.models.Book;
import com.lambdaschool.starthere.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "bookService")
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> findAll(Pageable pageable) throws EntityNotFoundException {
        List<Book> list = new ArrayList<>();
        bookRepository.findAll(pageable).iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Book update(Book book, long id) {
        Book newBook = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Long.toString(id)));

        if (book.getTitle() != null) {
            newBook.setTitle(book.getTitle());
        }

        if (book.getISBN() != null) {
            newBook.setISBN(book.getISBN());
        }

        if (book.getCopy() != 0) {
            newBook.setCopy(book.getCopy());
        }

        return bookRepository.save(newBook);
    }


    @Override
    public void addWrote(long bookid, long authorid) {

    }


    @Override
    public void delete(long id) {
        if (bookRepository.findById(id).isPresent()) {
            bookRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException(Long.toString(id));
        }
    }

}
