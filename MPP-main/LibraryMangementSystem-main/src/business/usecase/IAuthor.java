package business.usecase;

import java.util.List;

import business.exception.InvalidAuthorException;
import dataaccess.model.Author;

public interface IAuthor {

	public void addAuthor(Author author) throws InvalidAuthorException;

	public List<Author> getAllAuthors();
}
