package pl.com.bottega.cms.app.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class MovieDto {

    private String title;

    private String description;

    private List<String> actors;

    private List<String> genres;

    private Integer minAge;

    private Integer length;

    private List<ShowDto> shows;


    public MovieDto(String title, String description, List<String> actors, List<String> genres,
                    Integer minAge, Integer length) {
        this.title = title;
        this.description = description;
        this.actors = actors;
        this.genres = genres;
        this.minAge = minAge;
        this.length = length;
    }

    public MovieDto() {
    }

    public void addShow(ShowDto show) {
        if (shows == null)
            shows = new LinkedList<>();
        shows.add(show);
    }

    public void sortShowsByTime() {
        shows = shows.stream().sorted(Comparator.comparing(ShowDto::getTime)).collect(Collectors.toList());
    }
}
