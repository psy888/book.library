package com.psy888.book.library.controller;

import com.psy888.book.library.model.AuthorEntity;
import com.psy888.book.library.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/authors")
@RequiredArgsConstructor
public class AuthorRestController {

    private final AuthorService service;

    @GetMapping
    public ResponseEntity<Iterable<AuthorEntity>> getAuthorList() {
        return new ResponseEntity<>(service.getAuthorList(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<AuthorEntity> getAuthor(@PathVariable Long id) {
        return new ResponseEntity<>(service.getAuthor(id), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity removeAuthor(@PathVariable Long id){
        service.deleteAuthor(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AuthorEntity> createAuthor(@RequestBody AuthorEntity authorEntity){
        AuthorEntity savedAuthor = service.saveAuthor(authorEntity);
        return new ResponseEntity<>(savedAuthor, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<AuthorEntity> updateAuthor(@PathVariable Long id, @RequestBody AuthorEntity authorEntity){
        AuthorEntity updatedAuthor = service.updateAuthor(id, authorEntity);
        return new ResponseEntity<>(updatedAuthor, HttpStatus.OK);
    }
}
