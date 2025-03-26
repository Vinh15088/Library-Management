package org.example.Repository.impl;

import org.example.Repository.AuthorRepository;
import org.example.model.Author;
import org.example.utils.CSVFileUtils;

import java.util.ArrayList;
import java.util.List;

public class AuthorRepositoryFileImpl implements AuthorRepository {

    private static final String FILE_PATH = "data/authors.csv";
    private static final String CSV_HEADER = "id,name,email,phone";

    public AuthorRepositoryFileImpl() {
        CSVFileUtils.initCSVFile(FILE_PATH, CSV_HEADER);
    }

    @Override
    public void addAuthor(Author author) {
        List<Author> authors = getAllAuthors();

        if(authors == null) {
            authors = new ArrayList<Author>();
        }

        int newAuthor = CSVFileUtils.getNextId(authors, Author::getId);
        author.setId(newAuthor);

        authors.add(author);

        saveToFile(authors);
    }

    @Override
    public Author getAuthorById(Integer id) {
        return getAllAuthors().stream().filter(author -> author.getId().equals(id))
                .findFirst().orElse(null);
    }

    @Override
    public List<Author> getAllAuthors() {
        return CSVFileUtils.readAllFromCSVFile(FILE_PATH, this::parseAuthorFromCSV);
    }

    @Override
    public void updateAuthor(Author author) {
        List<Author> authors = getAllAuthors();
        boolean check = false;

        for(int i = 0; i < authors.size(); i++) {
            if(author.getId().equals(authors.get(i).getId())) {
                authors.set(i, author);
                check = true;
                break;
            }
        }

        if(check) {
            saveToFile(authors);
        } else {
            System.out.println("Author id not found");
        }
    }

    @Override
    public void deleteAuthor(Integer id) {
        List<Author> authors = getAllAuthors();
        boolean check = authors.removeIf(author -> author.getId().equals(id));

        if(check) {
            saveToFile(authors);
        } else {
            System.out.println("Author id not found");
        }
    }

    private void saveToFile(List<Author> authors) {
        CSVFileUtils.saveToCSV(FILE_PATH, CSV_HEADER, authors, this::convertAuthorToCSV);
    }

    private String convertAuthorToCSV(Author author) {
        return String.join(",",
                String.valueOf(author.getId()),
                author.getName(),
                author.getEmail(),
                author.getPhone()
        );
    }

    private Author parseAuthorFromCSV(String line) {
        String[] values = line.split(",");

        Author author = new Author();
        author.setId(Integer.parseInt(values[0].trim()));
        author.setName(values[1].trim());
        author.setEmail(values[2].trim());
        author.setPhone(values[3].trim());

        return author;
    }
}
