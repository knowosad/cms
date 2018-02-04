package pl.com.bottega.cms.app;

import pl.com.bottega.cms.app.dtos.MovieDto;

import java.time.LocalDate;
import java.util.List;

public interface MovieFinder {

    List<MovieDto> search(Long cinemaId, LocalDate date);
}
