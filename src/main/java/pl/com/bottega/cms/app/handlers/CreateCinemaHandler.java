package pl.com.bottega.cms.app.handlers;

import org.springframework.stereotype.Component;
import pl.com.bottega.cms.model.Cinema;
import pl.com.bottega.cms.model.repositories.CinemaRepository;
import pl.com.bottega.cms.model.commands.Command;
import pl.com.bottega.cms.model.commands.CreateCinemaCommand;
import pl.com.bottega.cms.model.commands.ValidationErrors;

import javax.transaction.Transactional;

/**
 * Created by freszczypior on 2017-12-22.
 */
@Component
public class CreateCinemaHandler implements Handler<CreateCinemaCommand> {

    private ValidationErrors errors;
    private CinemaRepository repository;

    public CreateCinemaHandler(ValidationErrors errors, CinemaRepository repository) {
        this.errors = errors;
        this.repository = repository;
    }

    @Transactional
    @Override
    public void handle(CreateCinemaCommand command) {
        validateExist(command);
        repository.save(new Cinema(command.getName(), command.getCity()));

    }

    private void validateExist(CreateCinemaCommand command) {
        if (cinemaExist(command)){
            errors.add(command.getName(), "such cinema already ifExist");
        }
    }

    private boolean cinemaExist(CreateCinemaCommand command) {
        return repository.ifExist(command.getName(), command.getCity());
    }

    @Override
    public Class<? extends Command> getSupportedCommandClass() {
        return CreateCinemaCommand.class;
    }
}
