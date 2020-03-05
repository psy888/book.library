package com.psy888.book.library.service;

import com.psy888.book.library.model.AuthorEntity;
import com.psy888.book.library.model.BookEntity;
import com.psy888.book.library.model.GenreEnum;
import com.psy888.book.library.repository.AuthorRepository;
import com.psy888.book.library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BatchUpdateUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import static java.util.Objects.isNull;


@Service
@RequiredArgsConstructor
@Slf4j
public class BookLibraryService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public void addNewBook(BookEntity book){
        AuthorEntity author = book.getAuthor();
        if(isNull(author)){
            throw new RuntimeException("Can't save book without author");
        }
        if(isNull(author.getId())) {
            author = authorRepository.save(author); //Метод save () возвращает сохраненную сущность, включая обновленное поле id .
            book.setAuthor(author);
        }
        bookRepository.save(book);
    }

    public AuthorEntity findAuthorByFullName(String firstName, String secondName, String lastName){
        AuthorEntity author = authorRepository.getAuthorEntityByFullName(firstName,secondName,lastName).orElse(null);
        if(isNull(author)){
            throw new RuntimeException("Can't find author with name :" + firstName + " " + secondName + " " + lastName);
        }
        return author;
    }

    public List<BookEntity> findBooksByGenre(Set<GenreEnum> genres){
        log.info("Log books list by genres");
        return bookRepository.findBookEntitiesByGenreIn(genres);
    }

    public List<BookEntity> findBooksByAuthor(String firstName, String secondName, String lastName){
        log.info("Log books list by genres");
        AuthorEntity author = findAuthorByFullName(firstName,secondName,lastName);
        return bookRepository.findBookEntitiesByAuthor(author);
    }
}
