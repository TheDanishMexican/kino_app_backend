package kea.kinoBackend.project.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import kea.kinoBackend.project.model.Movie;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MovieDTO {
    private int id;
    private String name;
    private String description;
    private String releaseDate;
    private String director;
    private List<String> actors;
    private List<String> genres;
    private String created;
    private String edited;

    public MovieDTO(Movie r, boolean includeAll) {
        this.id = r.getId();
        this.name = r.getName();
        this.description = r.getDescription();
        this.releaseDate = r.getReleaseDate().toString();
        this.director = r.getDirector();
        this.actors = r.getActors();
        this.genres = r.getGenres();
        if(includeAll){
            this.created = r.getCreated().toString();
            this.edited = r.getEdited().toString();
        }

    }

}
