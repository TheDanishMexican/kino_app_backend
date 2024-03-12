package kea.kinoBackend.project.service;

import kea.kinoBackend.project.dto.CinemaDTO;
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

//    public List<RecipeDTO> getAllRecipes(String category) {
//        List<Recipe> recipes = category == null ? recipeRepository.findAll() : recipeRepository.findByCategoryName(category);
//        List<RecipeDTO> recipeResponses = recipes.stream().map((r) -> new RecipeDTO(r,false)).toList();
//        return recipeResponses;
//    }

    public List<CinemaDTO> getAllCinemas() {
        List<Cinema> cinemaResponses = cinemaRepository.findAll();

    }

    private CinemaDTO toDTO(Cinema cinema) {
        // Implement your conversion logic here
        return new CinemaDTO(cinema.getId(), cinema.getName(), cinema.getLocation(), cinema.getHalls());
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

    public CinemaDTO addCinema(CinemaDTO request) {

    }
}
