package pl.com.bottega.cms.ui.controllers;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.com.bottega.cms.app.CommandGateway;
import pl.com.bottega.cms.model.commands.CreateMovieCommand;

/**
 * Created by freszczypior on 2017-12-23.
 */
@RestController
public class MovieController {

    private CommandGateway gateway;

    public MovieController(CommandGateway gateway) {
        this.gateway = gateway;
    }

    @PutMapping("/movies")
    public void create(@RequestBody CreateMovieCommand command){
        gateway.execute(command);
    }

}
