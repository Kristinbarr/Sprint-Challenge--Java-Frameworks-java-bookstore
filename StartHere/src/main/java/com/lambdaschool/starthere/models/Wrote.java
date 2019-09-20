package com.lambdaschool.starthere.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="wrote")
public class Wrote extends Auditable implements Serializable {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "authorid")
    @JsonIgnoreProperties({"wrote", "hibernateLazyInitializer"})
    private Author author;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bookid")
    @JsonIgnoreProperties({"wrote", "hibernateLazyInitializer"})
    private Book book;

    public Wrote() {}

    public Wrote(Author author, Book book) {
        this.author = author;
        this.book = book;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof Wrote))
        {
            return false;
        }
        Wrote that = (Wrote) o;
        return Objects.equals(getAuthor(), that.getAuthor()) && Objects.equals(getBook(), that.getBook());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getAuthor(), getBook());
    }
}
