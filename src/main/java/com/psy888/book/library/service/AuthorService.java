package com.psy888.book.library.service;

import com.psy888.book.library.model.AuthorEntity;
import com.psy888.book.library.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    public Iterable<AuthorEntity> getAuthorList(){
        return authorRepository.findAll();
    }

    public AuthorEntity getAuthor(Long id){
        return authorRepository.findById(id).orElse(null);
    }

    public void deleteAuthor(Long id){
        authorRepository.deleteById(id);
    }

    public AuthorEntity saveAuthor(AuthorEntity authorEntity){
        return authorRepository.save(authorEntity);
    }


    public AuthorEntity updateAuthor(Long id, AuthorEntity authorEntity) {
        if(id>0&&nonNull(authorEntity)){
            AuthorEntity oldAuthor = authorRepository.findById(id).orElse(new AuthorEntity());

            oldAuthor.setFirstName(authorEntity.getFirstName());
            oldAuthor.setSecondName(authorEntity.getSecondName());
            oldAuthor.setLastName(authorEntity.getLastName());
            oldAuthor.setBirthDate(authorEntity.getBirthDate());

            return authorRepository.save(oldAuthor);
        }
        return null;
    }
}