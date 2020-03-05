package com.psy888.book.library.repository;

import com.psy888.book.library.model.AuthorEntity;
import com.psy888.book.library.model.BookEntity;
import com.psy888.book.library.model.GenreEnum;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<BookEntity,Long> {

    List<BookEntity> findBookEntitiesByAuthor(AuthorEntity author);

    List<BookEntity> findBookEntitiesByGenreIn(Collection<GenreEnum> genres);

}
