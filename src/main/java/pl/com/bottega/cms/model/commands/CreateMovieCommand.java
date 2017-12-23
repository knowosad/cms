package pl.com.bottega.cms.model.commands;

import java.util.List;

/**
 * Created by freszczypior on 2017-12-23.
 */
public class CreateMovieCommand implements Command{

    private String title;
    private String description;
    private List<String> actors;
    private List<String> genres;
    private Integer minAge;
    private Integer length;

    public void validate(ValidateErrors errors){
        validatePresence(errors, "title", title);
        validatePresence(errors, "description", description);
        validatePresence(errors, "actors", actors);
        validatePresence(errors, "genres", genres);
        validatePresence(errors, "minAge", minAge);
        validatePresence(errors, "length", length);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getActors() {
        return actors;
    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public Integer getMinAge() {
        return minAge;
    }

    public void setMinAge(Integer minAge) {
        this.minAge = minAge;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }
}
