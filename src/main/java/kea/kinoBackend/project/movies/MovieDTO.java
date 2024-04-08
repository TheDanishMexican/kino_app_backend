package kea.kinoBackend.project.movies;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MovieDTO {

    private Integer id;
    private String name;
    private String posterUrl;
    private String description;
    private String releaseDate;
    private String director;
    private int duration;
    private List<String> actors;
    private List<String> genres;
    private String created;
    private String edited;

    public MovieDTO(Movie r, boolean includeAll) {
        this.id = r.getId();
        this.name = r.getName();
        this.posterUrl = r.getPosterUrl();
        this.description = r.getDescription();
        this.duration = r.getDuration();
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
