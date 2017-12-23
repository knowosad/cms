package pl.com.bottega.cms.ui.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.com.bottega.cms.app.CinemaFinder;
import pl.com.bottega.cms.app.CommandGateway;
import pl.com.bottega.cms.app.dtos.CinemaDto;
import pl.com.bottega.cms.model.commands.CreateCinemaCommand;

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
}
