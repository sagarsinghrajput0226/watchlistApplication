package com.example.sagarsingh.watchlist.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.example.sagarsingh.watchlist.entity.Movie;
import com.example.sagarsingh.watchlist.services.DatabaseService;

import jakarta.validation.Valid;

@RestController
public class MovieController {
	@Autowired
	DatabaseService databaseService;
@GetMapping("/watchlistItemForm")
public ModelAndView showWatchListForm(@RequestParam(required=false)Integer id) {
	String viewName="watchlistItemForm";
	Map<String,Object> model=new HashMap<>();
	if(id==null) {
		model.put("movie", new Movie());
	}
	else {
		model.put("movie",databaseService.getMovieById(id));
	}
//	Movie dummyMovie = new Movie();
//	dummyMovie.setTitle("dummy");
//	dummyMovie.setRating(0);
//	dummyMovie.setPriority(0);
//	dummyMovie.setComment("john doe");
//	model.put("watchlistItem", dummyMovie); 
	return new ModelAndView(viewName,model);
	
}
@PostMapping("/watchlistItemForm")
public ModelAndView submitWatchListForm(@Valid Movie movie,BindingResult bindingResult,@RequestParam("rating") float rating) {
	/*if id==null{
	 * create new movie
	 * else update*/
	if(bindingResult.hasErrors()) {
		//if error are there,redisplay the form and let user enter again
		return new ModelAndView("watchlistItemForm");
	}
	Integer id=movie.getId();
	if(id==null) {
		databaseService.create(movie,rating);
	}
	else {
		databaseService.update(movie,id);
	}
	databaseService.create(movie,rating);
	RedirectView rd=new RedirectView();
	rd.setUrl("/watchlist");
	return new ModelAndView(rd);
}
@GetMapping("/watchlist")
public ModelAndView getWatchlist() {
	String viewName="watchlist";
	Map<String,Object>model=new HashMap<>();
	List<Movie> movielist=databaseService.getAllMovies();
	model.put("watchlistrows",movielist);
	model.put("noofmovies",movielist.size() );
	return new ModelAndView(viewName,model);
}
@GetMapping("/index")
public ModelAndView getHome() {
	return new ModelAndView("index");
}
}
