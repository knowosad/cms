package pl.com.bottega.cms.model.commands;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by freszczypior on 2017-12-23.
 */
@Getter
@Setter
public class CreateMovieCommand implements Command{

    private String title;
    private String description;
    private List<String> actors;
    private List<String> genres;
    private Integer minAge;
    private Integer length;

    public void validate(ValidationErrors errors){
        validatePresence(errors, "title", title);
        validatePresence(errors, "description", description);
        validatePresence(errors, "actors", actors);
        validatePresence(errors, "genres", genres);
        validatePresence(errors, "minAge", minAge);
        validatePresence(errors, "length", length);
    }
}
