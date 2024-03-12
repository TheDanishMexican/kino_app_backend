package kea.kinoBackend.project.service;

import kea.kinoBackend.project.model.Cinema;
import kea.kinoBackend.project.repository.CinemaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CinemaService {
    private CinemaRepository cinemaRepository;

    public CinemaService(CinemaRepository cinemaRepository) {
        this.cinemaRepository = cinemaRepository;
    }

    public List<Cinema> getAllCinemas() {
        return cinemaRepository.findAll();
    }

//    public RecipeDTO addRecipe(RecipeDTO request) {
//        if (request.getId() != null) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You cannot provide the id for a new recipe");
//        }
//        Category category = categoryRepository.findByName(request.getCategory()).
//                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Only existing categories are allowed"));
//        Recipe newRecipe = new Recipe();
//        updateRecipe(newRecipe, request, category);
//        recipeRepository.save(newRecipe);
//        return new RecipeDTO(newRecipe,false);
//    }


}
