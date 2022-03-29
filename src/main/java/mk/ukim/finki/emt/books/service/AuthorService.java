package mk.ukim.finki.emt.books.service;

import mk.ukim.finki.emt.books.model.Author;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuthorService {

    List<Author> findAll();

}
