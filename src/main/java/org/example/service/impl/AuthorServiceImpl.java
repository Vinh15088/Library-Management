package org.example.service.impl;

import org.example.Repository.AuthorRepository;
import org.example.Repository.impl.AuthorRepositoryDBImpl;
import org.example.Repository.impl.AuthorRepositoryFileImpl;
import org.example.enums.StorageType;
import org.example.model.Author;
import org.example.service.AuthorService;

import java.util.List;

public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepositoryDB;
    private final AuthorRepository authorRepositoryFile;

    public AuthorServiceImpl() {
        this.authorRepositoryDB = new AuthorRepositoryDBImpl();
        this.authorRepositoryFile = new AuthorRepositoryFileImpl();
    }

    private AuthorRepository getRepository(StorageType storageType) {
        return storageType == StorageType.FILE ? authorRepositoryFile : authorRepositoryDB;
    }

    @Override
    public void addAuthor(Author author, StorageType storageType) {
        getRepository(storageType).addAuthor(author);
    }

    @Override
    public Author getAuthorById(Integer id, StorageType storageType) {
        Author author = getRepository(storageType).getAuthorById(id);

        if(author == null) {
            throw new RuntimeException("Author id " + id + " not exist");
        }

        return author;
    }

    @Override
    public List<Author> getAllAuthors(StorageType storageType) {
        return getRepository(storageType).getAllAuthors();
    }

    @Override
    public void updateAuthor(Author author, StorageType storageType) {
        getAuthorById(author.getId(), storageType);

        getRepository(storageType).updateAuthor(author);
    }

    @Override
    public void deleteAuthor(Integer id, StorageType storageType) {
        getAuthorById(id, storageType);

        getRepository(storageType).deleteAuthor(id);
    }
}
