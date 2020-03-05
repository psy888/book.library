package com.psy888.book.library.repository;

import com.psy888.book.library.model.AuthorEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends CrudRepository<AuthorEntity,Long> {

    Optional<AuthorEntity> getAuthorEntityByFirstNameAndSecondNameAndLastName(String firstName, String secondName, String lastName);

    @Query("from AuthorEntity a where a.firstName=?1 and a.secondName=?2 and a.lastName=?3")
    Optional<AuthorEntity> getAuthorEntityByFullName(String firstName, String secondName, String lastName);
}
