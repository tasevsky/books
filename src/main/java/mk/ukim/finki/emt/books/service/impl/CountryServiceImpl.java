package mk.ukim.finki.emt.books.service.impl;

import mk.ukim.finki.emt.books.model.Country;
import mk.ukim.finki.emt.books.repository.CountryRepository;
import mk.ukim.finki.emt.books.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> findAll() {
        return this.countryRepository.findAll();
    }

}
