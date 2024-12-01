package com.example.sagarsingh.watchlist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sagarsingh.watchlist.entity.Movie;
import com.example.sagarsingh.watchlist.repositories.MovieRepo;

@Service
public class DatabaseService {
    @Autowired
    MovieRepo movieRepo;
    @Autowired
    RatingService ratingService;

    public void create(Movie movie,float rating) {
        String Imdbrating = ratingService.getMovieRating(movie.getTitle());
        if (Imdbrating != null && !Imdbrating.trim().isEmpty()) {
            try {
                movie.setRating(Float.parseFloat(Imdbrating));
            } catch (NumberFormatException e) {
                System.err.println("Invalid rating format: " + e.getMessage());
                movie.setRating(rating);
            }
        } else {
            movie.setRating(rating);
        }
        movieRepo.save(movie);
    }

    public List<Movie> getAllMovies() {
        return movieRepo.findAll();
    }

    public Movie getMovieById(Integer id) {
        return movieRepo.findById(id).orElse(null);
    }

    public void update(Movie movie, Integer id) {
        Movie toBeUpdated = getMovieById(id);
        if (toBeUpdated != null) {
            toBeUpdated.setTitle(movie.getTitle());
            toBeUpdated.setRating(movie.getRating());
            toBeUpdated.setComment(movie.getComment());
            toBeUpdated.setPriority(movie.getPriority());
            movieRepo.save(toBeUpdated);
        }
    }
}

