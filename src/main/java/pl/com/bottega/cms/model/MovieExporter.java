package pl.com.bottega.cms.model;

import pl.com.bottega.cms.app.dtos.ShowDto;

import java.util.List;

public interface MovieExporter {

    void addTitle(String title);

    void addDescription(String description);

    void addActors(List<String> actors);

    void addGenres(List<String> genres);

    void addAge(Integer age);

    void addLength(Integer length);

    void addShow(ShowDto showDto);


}
