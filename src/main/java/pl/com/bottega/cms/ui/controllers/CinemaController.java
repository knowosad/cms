package pl.com.bottega.cms.ui.controllers;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import pl.com.bottega.cms.app.CinemaFinder;
import pl.com.bottega.cms.app.CommandGateway;
import pl.com.bottega.cms.app.MovieFinder;
import pl.com.bottega.cms.app.dtos.CinemaDto;
import pl.com.bottega.cms.app.dtos.MovieDto;
import pl.com.bottega.cms.model.commands.CreateCinemaCommand;
import pl.com.bottega.cms.model.commands.CreateShowsCommand;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by freszczypior on 2017-12-22.
 */
@RestController
public class CinemaController {

    private CommandGateway gateway;
    private MovieFinder movieFinder;
    private CinemaFinder cinemaFinder;

    public CinemaController(CommandGateway gateway, MovieFinder movieFinder, CinemaFinder finder) {
        this.gateway = gateway;
        this.movieFinder = movieFinder;
        this.cinemaFinder = finder;
    }

    @PutMapping("/cinemas")
    public void create(@RequestBody CreateCinemaCommand cmd){
        gateway.execute(cmd);
    }

    @GetMapping("/cinemas")
    public List<CinemaDto> getAll(){
        return cinemaFinder.getAll();
    }

    @PutMapping("/cinemas/{cinemaId}/shows")
    public void createShows(@PathVariable Long cinemaId, @RequestBody CreateShowsCommand cmd){
        cmd.setCinemaId(cinemaId);
        gateway.execute(cmd);
    }
    @GetMapping("cinemas/{cinemaId}/movies")
    public List<MovieDto> getShows(@PathVariable Long cinemaId,
                                   @RequestParam @DateTimeFormat(pattern = "yyyy/MM/dd") LocalDate date){
        return movieFinder.search(cinemaId, date);
    }
}
