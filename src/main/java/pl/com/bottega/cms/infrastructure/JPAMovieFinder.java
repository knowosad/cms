package pl.com.bottega.cms.infrastructure;

import org.springframework.stereotype.Component;
import pl.com.bottega.cms.app.MovieFinder;
import pl.com.bottega.cms.app.dtos.MovieDto;
import pl.com.bottega.cms.app.dtos.MovieDtoBuilder;
import pl.com.bottega.cms.app.dtos.ShowDtoBuilder;
import pl.com.bottega.cms.model.Cinema;
import pl.com.bottega.cms.model.Movie;
import pl.com.bottega.cms.model.Show;


import pl.com.bottega.cms.model.Show_;
import pl.com.bottega.cms.model.commands.CommandInvalidException;
import pl.com.bottega.cms.model.commands.ValidationErrors;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class JPAMovieFinder implements MovieFinder {

    private EntityManager entityManager;

    public JPAMovieFinder(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<MovieDto> search(Long cinemaId, LocalDate date) {
        validateCriteria(cinemaId, date);
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Show> cq = cb.createQuery(Show.class);
        Root<Show> root = cq.from(Show.class);
        root.fetch(Show_.movie);
        Predicate predicate = buildPredicate(cinemaId, date, cb, root);
        cq.where(predicate);
        cq.distinct(true);
        List<Show> shows = entityManager.createQuery(cq).getResultList();
        return createMovieDtoList(shows);
    }

    private List<MovieDto> createMovieDtoList(List<Show> shows) {
        List<MovieDto> movieDtosList = new LinkedList<>();
        Set<Movie> movies = shows.stream().map(Show::getMovie).collect(Collectors.toSet());
//        MovieDto movieDto;
//        for (Movie movie : movies) {
//            movieDto = new MovieDto();
//            ShowDto showDto = new ShowDto();
//            for (Show show : shows) {
//                if (movie.equals(show.getMovie())) {
//                    showDto = new ShowDto();
//                    movieDto.setTitle(movie.getTitle());
//                    movieDto.setDescription(movie.getDescription());
//                    movieDto.setActors(movie.getActors());
//                    movieDto.setGenres(movie.getGenres());
//                    movieDto.setMinAge(movie.getMinAge());
//                    movieDto.setLength(movie.getLength());
//                    showDto.setId(show.getId());
//                    showDto.setTime(show.getDateTime().toLocalTime());
//                }
//                movieDto.addShow(showDto);
//            }
//            movieDto.sortShowsByTime();
//            movieDtosList.add(movieDto);
//        }
        MovieDtoBuilder movieDtoBuilder = new MovieDtoBuilder();
        ShowDtoBuilder showDtoBuilder = new ShowDtoBuilder();
        for (Movie movie : movies) {
            movie.export(movieDtoBuilder);
            for (Show show : shows) {
                if (movie.equals(show.getMovie())) {
                    show.export(showDtoBuilder);
                    movieDtoBuilder.addShow(showDtoBuilder.build());
                }
            }
            MovieDto movieDto = movieDtoBuilder.build();
            movieDto.sortShowsByTime();
            movieDtosList.add(movieDto);
        }
        return movieDtosList;
    }
    @Override
    public List<MovieDto> getAll() {
        //TODO przy query nie widzi konstruktora MovieDto
//        Query query = entityManager.createQuery(
//                "select new pl.com.bottega.cms.app.dtos.MovieDto(m.id, m.title, m.description, m.actors, m.genres, m.minAge, m.length) from Movie m");
//        return query.getResultList();

        Query query = entityManager.createQuery("select m from Movie m");
        List<Movie> movies = query.getResultList();
        List<MovieDto> dtos = new LinkedList<>();
        convertMovieListToMovieDtoList(movies, dtos);
        return dtos;
    }

    private void convertMovieListToMovieDtoList(List<Movie> movies, List<MovieDto> dtos) {
        MovieDto movieDto;
        for (Movie movie: movies) {
            movieDto = new MovieDto(movie.getId(), movie.getTitle(), movie.getDescription(), movie.getActors(),
                    movie.getGenres(), movie.getMinAge(), movie.getLength());
            dtos.add(movieDto);
        }
    }

    private Predicate buildPredicate(Long cinemaId, LocalDate date, CriteriaBuilder cb, Root show) {
        Predicate predicate = cb.conjunction();
        predicate = addCinemaIdPredicate(cinemaId, cb, show, predicate);
        predicate = addDatePredicate(date, cb, show, predicate);
        return predicate;
    }


    private Predicate addCinemaIdPredicate(Long cinemaId, CriteriaBuilder cb, Root<Show> show, Predicate predicate) {
        return predicate = cb.and(predicate, cb.equal(show.get(Show_.cinema), cinemaId));
    }

    private Predicate addDatePredicate(LocalDate date, CriteriaBuilder cb, Root<Show> show, Predicate predicate) {
        return predicate = cb.and(predicate,
                cb.between(show.get("dateTime"), date.atStartOfDay(), date.plusDays(1).atStartOfDay()));
    }

    private void validateCriteria(Long cinemaId, LocalDate date) {
        validateIfTheCinemaExist(cinemaId);
        validateTheDate(date);
    }

    private void validateTheDate(LocalDate date) {
        if (date.isBefore(LocalDate.now()))
            throw new CommandInvalidException(new ValidationErrors("date", "Given date can't be in the past"));
    }

    private void validateIfTheCinemaExist(Long cinemaId) {
        if (entityManager.find(Cinema.class, cinemaId) == null)
            throw new CommandInvalidException(new ValidationErrors("cinemaId", "Such cinemaId don't exist"));
    }
}
