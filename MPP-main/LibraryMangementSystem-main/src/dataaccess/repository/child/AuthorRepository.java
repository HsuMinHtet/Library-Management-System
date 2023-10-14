package dataaccess.repository.child;

import business.exception.InvalidAuthorException;
import business.exception.InvalidMemberException;
import dataaccess.Auth;
import dataaccess.model.Author;
import dataaccess.DataAccess;
import dataaccess.repository.BaseRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AuthorRepository extends BaseRepository {
    public AuthorRepository(DataAccess dataAccess) {
        this.dataAccess = dataAccess;
    }

    public void addAuthor(Author author) throws InvalidAuthorException{
        if (author == null) {
            throw new InvalidAuthorException("Author is null");
        }

        if (author.getAuthorId().isEmpty()) {
            throw new InvalidAuthorException("Invalid Author Id");
        }

        if (author.getFirstName().isEmpty()) {
            throw new InvalidAuthorException("Invalid First Name");
        }

        if (author.getLastName().isEmpty()) {
            throw new InvalidAuthorException("Invalid Last Name");
        }

        if (searchAuthor(author.getAuthorId()) != null) {
            throw new InvalidAuthorException("Author ID already exist");
        }

        dataAccess.saveAuthor(author);
    }
    public List<Author> getAllAuthors() {
        HashMap<String, Author> authors = dataAccess.readAuthorMap();
        if (authors == null)
            return null;
        return new ArrayList<>(authors.values());
    }

    public Author searchAuthor(String id){
        HashMap<String, Author> map = dataAccess.readAuthorMap();
        return map.get(id);
    }
}
