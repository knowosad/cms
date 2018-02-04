package pl.com.bottega.cms.model;

import lombok.Getter;
import pl.com.bottega.cms.app.dtos.MovieDtoBuilder;
import pl.com.bottega.cms.model.commands.CreateMovieCommand;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by freszczypior on 2017-12-23.
 */
@Entity
@Table(name = "movies")
@Getter
public class Movie {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String description;

    @ElementCollection
    private List<String> actors;

    @ElementCollection
    private List<String> genres;

    private Integer minAge;

    private Integer length;

    public Movie() {
    }

    public Movie(String title, String description, List<String> actors, List<String> genres, Integer minAge, Integer length) {
        this.title = title;
        this.description = description;
        this.actors = actors;
        this.genres = genres;
        this.minAge = minAge;
        this.length = length;
    }

    public void export(MovieExporter exporter){
        exporter.addActors(actors.stream().collect(Collectors.toList()));
        exporter.addAge(minAge);
        exporter.addDescription(description);
        exporter.addGenres(genres.stream().collect(Collectors.toList()));
        exporter.addLength(length);
        exporter.addTitle(title);
    }
}
