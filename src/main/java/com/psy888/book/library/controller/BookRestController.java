package com.psy888.book.library.controller;

import com.psy888.book.library.model.BookEntity;
import com.psy888.book.library.model.GenreEnum;
import com.psy888.book.library.service.BookLibraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookRestController {

    private final BookLibraryService service;

    //request localhost:8080/api/books/search?name=Инокентий&lastName=Пупырышкин&secondName=Педросович
    @GetMapping("search")
    public ResponseEntity<List<BookEntity>> findBooks(@RequestParam String name,
                                                      @RequestParam String lastName,
                                                      @RequestParam String secondName){

        return new ResponseEntity<>(service.findBooksByAuthor(name,secondName,lastName), HttpStatus.OK);

    }

    // request example: localhost:8080/api/books/filter?genre=ROMAN,FANTASY
    @GetMapping("filter")
    public ResponseEntity<List<BookEntity>> findBooksByGenre(@RequestParam Set<GenreEnum> genre){
        return new ResponseEntity<>(service.findBooksByGenre(genre), HttpStatus.OK);
    }


}
