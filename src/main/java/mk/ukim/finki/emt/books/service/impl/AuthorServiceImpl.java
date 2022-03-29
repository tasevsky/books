package mk.ukim.finki.emt.books.service.impl;

import mk.ukim.finki.emt.books.model.Author;
import mk.ukim.finki.emt.books.repository.AuthorRepository;
import mk.ukim.finki.emt.books.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> findAll() {
        return this.authorRepository.findAll();
    }
}
