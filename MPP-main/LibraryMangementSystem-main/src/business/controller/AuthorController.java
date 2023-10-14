package business.controller;

import java.util.List;

import business.exception.InvalidAuthorException;
import business.usecase.IAuthor;
import dataaccess.DataAccessFacade;
import dataaccess.model.Author;
import dataaccess.repository.child.AuthorRepository;

public class AuthorController extends BaseController implements IAuthor {
	AuthorController() {
	}
	@Override
	public List<Author> getAllAuthors() {
		AuthorRepository repo = new AuthorRepository(new DataAccessFacade());
		return repo.getAllAuthors();
	}

	@Override
	public void addAuthor(Author author) throws InvalidAuthorException {
		AuthorRepository repo = new AuthorRepository(new DataAccessFacade());
		repo.addAuthor(author);

	}
}
