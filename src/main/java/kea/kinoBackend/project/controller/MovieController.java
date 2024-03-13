package kea.kinoBackend.project.controller;



import kea.kinoBackend.project.dto.MovieDTO;
import kea.kinoBackend.project.model.Movie;
import kea.kinoBackend.project.service.MovieService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/movies")
public class MovieController {

    private MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public List<MovieDTO> getAllMovies(@RequestParam(required = false) String genre) {
       if(genre != null) {
           System.out.println("Genre: " + genre);
       }
        return movieService.getAllMovies(genre);
    }

    @GetMapping(path ="/{id}")
    public MovieDTO getMovieById(@PathVariable int id) {
        return movieService.getMovieById(id);
    }

    @PostMapping
    public MovieDTO addMovie(@RequestBody MovieDTO request) {
        return movieService.addMovie(request);
    }

    @PutMapping(path = "/{id}")
    public MovieDTO editMovie(@RequestBody MovieDTO request,@PathVariable int id) {
        return movieService.editMovie(request,id);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteMovie(@PathVariable int id) {
        movieService.deleteMovie(id);
    }






}
