package kea.kinoBackend.project.service;

import kea.kinoBackend.project.dto.MovieDTO;
import kea.kinoBackend.project.model.Movie;
import kea.kinoBackend.project.repository.MovieRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
public class MovieService {
    private MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<MovieDTO> getAllMovies(String genre) {
        List<Movie> movies = genre == null ? movieRepository.findAll() : movieRepository.findByGenresContaining(genre);
        List<MovieDTO> movieResponses = movies.stream().map((r) -> new MovieDTO(r,false)).toList();
        return movieResponses;
    }

    public MovieDTO getMovieByName(String name) {
        Movie movie = movieRepository.findById(name).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found"));
        return new MovieDTO(movie,false);
    }

    public MovieDTO addMovie(MovieDTO request) {
        Movie newMovie = new Movie();
        updateMovie(newMovie, request);
        movieRepository.save(newMovie);
        return new MovieDTO(newMovie,false);
    }

    public MovieDTO editMovie(MovieDTO request, String name) {
        Movie movieToEdit = movieRepository.findById(name).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found"));
        updateMovie(movieToEdit, request);
        movieRepository.save(movieToEdit);
        return new MovieDTO(movieToEdit,false);
    }

    public ResponseEntity deleteMovie(String name) {
        Movie movie = movieRepository.findById(name).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found"));
        movieRepository.deleteById(name);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    private void updateMovie(Movie original, MovieDTO request) {
        original.setName(request.getName());
        original.setDescription(request.getDescription());
        if (request.getReleaseDate() != null) {
            LocalDate releaseDate = LocalDate.parse(request.getReleaseDate());
            original.setReleaseDate(releaseDate);
        }
        original.setDirector(request.getDirector());
        original.setActors(request.getActors());
        original.setGenres(request.getGenres());

    }
}
