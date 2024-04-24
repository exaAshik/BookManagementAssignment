package com.BookManageMent.Book.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity(name = "Books")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;


    private String title;

    private String isbn;

    private String publisher;

    private String year;

    private Boolean isAcademic;

//    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL,mappedBy = "book")
//    private List<Author> authorList;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "Book_Author",
    joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "Id"),
    inverseJoinColumns = @JoinColumn(name = "author_id",referencedColumnName = "Id"))
    Set<Author>authorList = new HashSet<>();






}

