package kea.kinoBackend.project.movies;



import io.swagger.v3.oas.annotations.Operation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/movies")
public class MovieController {

    private MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @Operation(summary = "Get all movies", description = "Get a list of all movies")
    @GetMapping
    public List<MovieDTO> getAllMovies(@RequestParam(required = false) String genre) {
       if(genre != null) {
           System.out.println("Genre: " + genre);
       }
        return movieService.getAllMovies(genre);
    }

    @Operation(summary = "Get one movie", description = "Get a movie by ID")
    @GetMapping(path ="/{id}")
    public MovieDTO getMovieById(@PathVariable int id) {
        return movieService.getMovieById(id);
    }

    @Operation(summary = "Add a new movie", description = "Add a new movie")
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public MovieDTO addMovie(@RequestBody MovieDTO request) {
        return movieService.addMovie(request);
    }

    @Operation(summary = "Update a movie", description = "Update a movie")
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping(path = "/{id}")
    public MovieDTO editMovie(@RequestBody MovieDTO request,@PathVariable int id) {
        return movieService.editMovie(request,id);
    }

    @Operation(summary = "Delete a movie", description = "Delete a movie")
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping(path = "/{id}")
    public void deleteMovie(@PathVariable int id) {
        movieService.deleteMovie(id);
    }






}
