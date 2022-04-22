package mk.ukim.finki.emt.books.service.impl;

import mk.ukim.finki.emt.books.model.Author;
import mk.ukim.finki.emt.books.model.Book;
import mk.ukim.finki.emt.books.model.dto.BookDto;
import mk.ukim.finki.emt.books.model.enumerations.BookCategory;
import mk.ukim.finki.emt.books.model.exceptions.AuthorNotFoundException;
import mk.ukim.finki.emt.books.model.exceptions.BookNotFoundException;
import mk.ukim.finki.emt.books.repository.AuthorRepository;
import mk.ukim.finki.emt.books.repository.BookRepository;
import mk.ukim.finki.emt.books.service.BookService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Optional<Book> findByName(String name) {
        return bookRepository.findByName(name);
    }

    @Override
    @Transactional
    public Optional<Book> save(String name, BookCategory category, Long authorId, Integer availableCopies) {
        Author author = this.authorRepository.findById(authorId)
                .orElseThrow(() -> new AuthorNotFoundException(authorId));

        this.bookRepository.deleteByName(name);
        Book book = new Book(name, category, author, availableCopies);
        this.bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public Optional<Book> save(BookDto bookDto) {
        Author author = this.authorRepository.findById(bookDto.getAuthor())
                .orElseThrow(() -> new AuthorNotFoundException(bookDto.getAuthor()));

        this.bookRepository.deleteByName(bookDto.getName());
        Book book = new Book(bookDto.getName(), bookDto.getCategory(), author, bookDto.getAvailableCopies());
        this.bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    @Transactional
    public Optional<Book> edit(Long id, String name, BookCategory category, Long authorId, Integer availableCopies) {
        Book book = this.bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
        Author author = this.authorRepository.findById(authorId)
                .orElseThrow(() -> new AuthorNotFoundException(authorId));

        book.setName(name);
        book.setCategory(category);
        book.setAuthor(author);
        book.setAvailableCopies(availableCopies);
        this.bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public Optional<Book> edit(Long id, BookDto bookDto) {
        Book book = this.bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
        Author author = this.authorRepository.findById(bookDto.getAuthor())
                .orElseThrow(() -> new AuthorNotFoundException(bookDto.getAuthor()));

        book.setName(bookDto.getName());
        book.setCategory(bookDto.getCategory());
        book.setAuthor(author);
        book.setAvailableCopies(bookDto.getAvailableCopies());
        this.bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public void deleteById(Long id) {
        this.bookRepository.deleteById(id);
    }

    @Override
    public Optional<Book> take(Long id) {
        Book book = this.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
        if (book.getAvailableCopies() > 0)
            book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepository.save(book);
        return Optional.of(book);
    }
}
