package mk.ukim.finki.emt.books.web;

import mk.ukim.finki.emt.books.model.Author;
import mk.ukim.finki.emt.books.model.Book;
import mk.ukim.finki.emt.books.model.dto.BookDto;
import mk.ukim.finki.emt.books.model.enumerations.BookCategory;
import mk.ukim.finki.emt.books.service.AuthorService;
import mk.ukim.finki.emt.books.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class BookRestController {

    private final BookService bookService;
    private final AuthorService authorService;

    public BookRestController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping({"/", "/books"})
    private List<Book> findAll() {
        return this.bookService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id) {
        return this.bookService.findById(id)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Book> save(@RequestBody BookDto bookDto) {
        return this.bookService.save(bookDto)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Book> save(@PathVariable Long id, @RequestBody BookDto bookDto) {
        return this.bookService.edit(id, bookDto)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        this.bookService.deleteById(id);
        if(this.bookService.findById(id).isEmpty()) return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/categories")
    public BookCategory[] findCategories(){
        return BookCategory.values();
    }

    @GetMapping("/authors")
    private List<Author> findAuthors() {
        return this.authorService.findAll();
    }

    @PutMapping("/take/{id}")
    public ResponseEntity<Book> take(@PathVariable Long id) {
        return this.bookService.take(id)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
