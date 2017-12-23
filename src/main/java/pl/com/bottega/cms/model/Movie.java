package pl.com.bottega.cms.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by freszczypior on 2017-12-23.
 */
@Entity
@Table(name = "movies")
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

    public Movie(String title, String description, List<String> actors, List<String> genres, Integer minAge, Integer length) {
        this.title = title;
        this.description = description;
        this.actors = actors;
        this.genres = genres;
        this.minAge = minAge;
        this.length = length;
    }

    public Movie() {
    }
}
