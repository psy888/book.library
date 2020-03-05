package com.psy888.book.library.model;

import lombok.Data;

import javax.persistence.*;
import java.time.Year;

@Entity
@Data
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int pagesCount;

    private Year releaseDate;

    private String booking;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private GenreEnum genre;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private AuthorEntity author;


}
