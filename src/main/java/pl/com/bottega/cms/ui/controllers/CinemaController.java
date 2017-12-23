package pl.com.bottega.cms.ui.controllers;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.com.bottega.cms.app.CommandGateway;
import pl.com.bottega.cms.model.commands.CreateCinemaCommand;

/**
 * Created by freszczypior on 2017-12-22.
 */
@RestController
public class CinemaController {

    private CommandGateway gateway;

    public CinemaController(CommandGateway gateway) {
        this.gateway = gateway;
    }

    @PutMapping("/cinemas")
    void create(@RequestBody CreateCinemaCommand cmd){
        gateway.execute(cmd);
    }

}
