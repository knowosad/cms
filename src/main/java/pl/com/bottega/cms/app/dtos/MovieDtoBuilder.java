package pl.com.bottega.cms.app.dtos;

import pl.com.bottega.cms.model.MovieExporter;

import java.util.List;

public class MovieDtoBuilder implements MovieExporter{

    private MovieDto movieDto = new MovieDto();

    public MovieDto build(){
        MovieDto movieDtoResult = movieDto;
        movieDto = new MovieDto();
        return movieDtoResult;
    }

    @Override
    public void addTitle(String title) {
        movieDto.setTitle(title);
    }

    @Override
    public void addDescription(String description) {
        movieDto.setDescription(description);
    }

    @Override
    public void addActors(List<String> actors) {
        movieDto.setActors(actors);
    }

    @Override
    public void addGenres(List<String> genres) {
        movieDto.setGenres(genres);
    }

    @Override
    public void addAge(Integer age) {
        movieDto.setMinAge(age);
    }

    @Override
    public void addLength(Integer length) {
        movieDto.setLength(length);
    }

    @Override
    public void addShow(ShowDto showDto) {
        movieDto.addShow(showDto);
    }
}
