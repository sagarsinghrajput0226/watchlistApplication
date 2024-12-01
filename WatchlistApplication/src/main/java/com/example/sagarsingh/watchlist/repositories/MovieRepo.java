package com.example.sagarsingh.watchlist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sagarsingh.watchlist.entity.Movie;
@Repository
public interface MovieRepo extends JpaRepository<Movie,Integer> {

}
