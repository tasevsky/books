package mk.ukim.finki.emt.books.service;

import mk.ukim.finki.emt.books.model.Book;
import mk.ukim.finki.emt.books.model.dto.BookDto;
import mk.ukim.finki.emt.books.model.enumerations.BookCategory;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> findAll();

    Optional<Book> findById(Long id);

    Optional<Book> findByName(String name);

    Optional<Book> save(String name, BookCategory category, Long authorId, Integer availableCopies);

    Optional<Book> save(BookDto bookDto);

    Optional<Book> edit(Long id, String name, BookCategory category, Long authorId, Integer availableCopies);

    Optional<Book> edit(Long id, BookDto bookDto);

    void deleteById(Long id);

    Optional<Book> take(Long id);
}
