package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.models.Author;
import com.lambdaschool.starthere.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

//@Transactional
@Service(value = "authorService")
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorrepo;

    @Transactional
    public List<Author> findAll() throws EntityNotFoundException {
        List<Author> list = new ArrayList<>();
        authorrepo.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

}
