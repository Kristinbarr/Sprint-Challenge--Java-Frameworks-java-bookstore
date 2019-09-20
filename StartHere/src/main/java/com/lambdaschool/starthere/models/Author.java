package com.lambdaschool.starthere.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "author")
public class Author extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long authorid;

    @Column(unique = true,
            nullable = false)
    private String lastname;
    private String firstname;

    @OneToMany(mappedBy = "book",
            cascade = CascadeType.ALL)
    @JsonIgnoreProperties("book")
    private List<AuthorBooks> authorBooks = new ArrayList<>();

    public Author(String lastname, String firstname, List<AuthorBooks> authorBooks) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.authorBooks = authorBooks;
    }

    public long getAuthorid() {
        return authorid;
    }

    public void setAuthorid(long authorid) {
        this.authorid = authorid;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public List<AuthorBooks> getAuthorBooks() {
        return authorBooks;
    }

    public void setAuthorBooks(List<AuthorBooks> authorBooks) {
        this.authorBooks = authorBooks;
    }
}
