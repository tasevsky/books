package mk.ukim.finki.emt.books.model.dto;

import lombok.Data;
import mk.ukim.finki.emt.books.model.Author;
import mk.ukim.finki.emt.books.model.enumerations.BookCategory;

@Data
public class BookDto {

    private String name;

    private BookCategory category;

    private Long author;

    private Integer availableCopies;

    public BookDto(String name, BookCategory category, Long author, Integer availableCopies) {
        this.name = name;
        this.category = category;
        this.author = author;
        this.availableCopies = availableCopies;
    }

    public BookDto() {
    }

}
