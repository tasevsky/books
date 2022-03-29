package mk.ukim.finki.emt.books.repository;

import mk.ukim.finki.emt.books.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

}
