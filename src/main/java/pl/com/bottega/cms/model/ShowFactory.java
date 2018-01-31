package pl.com.bottega.cms.model;

import org.springframework.stereotype.Component;
import pl.com.bottega.cms.model.commands.CreateShowsCommand;
import pl.com.bottega.cms.model.repositories.CinemaRepository;
import pl.com.bottega.cms.model.repositories.MovieRepository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by freszczypior on 2017-12-26.
 */
@Component
public class ShowFactory {

    private CinemaRepository cinemaRepository;
    private MovieRepository movieRepository;

    public ShowFactory(CinemaRepository cinemaRepository, MovieRepository movieRepository) {
        this.cinemaRepository = cinemaRepository;
        this.movieRepository = movieRepository;
    }

    public Collection<Show> createShows(CreateShowsCommand cmd) {
        Set<LocalDateTime> dates;
        Cinema cinema = cinemaRepository.get(cmd.getCinemaId());
        Movie movie = movieRepository.get(cmd.getMovieId());
        if (containsShowsCalendar(cmd)) {
            dates = getDatesFromCalendar(cmd);
        } else
            dates = getDatesFromList(cmd);
        return dates.stream().map((dateTime) -> new Show(cinema, movie, dateTime)).collect(Collectors.toList());
    }

    private Set<LocalDateTime> getDatesFromList(CreateShowsCommand cmd) {
        return cmd.getDates();
    }

    private Set<LocalDateTime> getDatesFromCalendar(CreateShowsCommand cmd) {
        return cmd.getCalendar().generateDates();
    }

    private static boolean containsShowsCalendar(CreateShowsCommand cmd) {
        return cmd.getCalendar() != null;
    }

}
