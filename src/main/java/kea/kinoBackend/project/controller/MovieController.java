package kea.kinoBackend.project.controller;



import kea.kinoBackend.project.model.Movie;
import kea.kinoBackend.project.service.MovieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/movies")
public class MovieController {

    private MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public List<Movie> getAllMovies() {
        List<Movie> movies = movieService.getAllMovies();
        for (Movie movieitem : movies) {
            System.out.println(movieitem.getName());
        }
        return movieService.getAllMovies();
    }


}
