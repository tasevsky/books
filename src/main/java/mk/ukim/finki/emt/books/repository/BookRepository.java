package mk.ukim.finki.emt.books.repository;

import mk.ukim.finki.emt.books.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByName(String name);

    void deleteByName(String name);

}
