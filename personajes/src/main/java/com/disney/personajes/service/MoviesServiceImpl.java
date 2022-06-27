package com.disney.personajes.service;

import com.disney.personajes.model.Movies;
import com.disney.personajes.repository.MoviesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MoviesServiceImpl implements MoviesService {

    @Autowired
    MoviesRepository moviesRepository;

    @Override
    public List<Movies> getAllMovies() {
        return moviesRepository.findAll();
    }

    @Override
    public Movies getMoviesById(Long id) {
        Optional<Movies> opt = moviesRepository.findById(id);

        if (opt.isPresent()){
            return opt.get();
        }else{
            return new Movies();
        }
    }

    @Override
    public Movies createMovies(Movies movies) {
        return moviesRepository.save(movies);
    }

    @Override
    public Movies updateMovies(Movies movies) {
        return moviesRepository.save(movies);
    }

    @Override
    public void deleteMovies(Long id) {
        moviesRepository.deleteById(id);
    }

    @Override
    public List<Movies> findByTitle(String title) {
        return moviesRepository.findByTitle(title);
    }

}
