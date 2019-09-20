package com.lambdaschool.starthere.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@ApiModel(value = "Author", description = "The Author Entity")
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

    @ApiModelProperty(name = "books", value = "List of Author Books")
    @OneToMany(mappedBy = "author",
            cascade = CascadeType.ALL)
    @JsonIgnoreProperties("author")
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
