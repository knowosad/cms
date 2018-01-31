package pl.com.bottega.cms.ui.controllers;

import org.springframework.web.bind.annotation.*;
import pl.com.bottega.cms.app.CinemaFinder;
import pl.com.bottega.cms.app.CommandGateway;
import pl.com.bottega.cms.app.dtos.CinemaDto;
import pl.com.bottega.cms.model.commands.CreateCinemaCommand;
import pl.com.bottega.cms.model.commands.CreateShowsCommand;

import java.util.List;

/**
 * Created by freszczypior on 2017-12-22.
 */
@RestController
public class CinemaController {

    private CommandGateway gateway;

    private CinemaFinder finder;

    public CinemaController(CommandGateway gateway, CinemaFinder finder) {
        this.gateway = gateway;
        this.finder = finder;
    }

    @PutMapping("/cinemas")
    public void create(@RequestBody CreateCinemaCommand cmd){
        gateway.execute(cmd);
    }

    @GetMapping("/cinemas")
    public List<CinemaDto> getAll(){
        return finder.getAll();
    }

    @PutMapping("/cinemas/{cinemaId}/shows")
    public void createShows(@PathVariable Long cinemaId, @RequestBody CreateShowsCommand cmd){
        cmd.setCinemaId(cinemaId);
        gateway.execute(cmd);
    }
}
