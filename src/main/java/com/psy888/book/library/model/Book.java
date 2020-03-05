package com.psy888.book.library.model;

import lombok.Data;

import javax.persistence.*;
import java.time.Year;

@Entity
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int pagesCount;

    private Year releaseDate;

    private String booking;


}
