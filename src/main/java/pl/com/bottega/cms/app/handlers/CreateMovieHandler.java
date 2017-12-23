package pl.com.bottega.cms.app.handlers;

import org.springframework.stereotype.Component;
import pl.com.bottega.cms.model.Movie;
import pl.com.bottega.cms.model.commands.Command;
import pl.com.bottega.cms.model.commands.CreateMovieCommand;
import pl.com.bottega.cms.model.repositories.MovieRepository;

import javax.transaction.Transactional;

/**
 * Created by freszczypior on 2017-12-23.
 */
@Component
public class CreateMovieHandler implements Handler<CreateMovieCommand> {

    private MovieRepository repository;

    public CreateMovieHandler(MovieRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public void handle(CreateMovieCommand cmd) {
        repository.save(new Movie(cmd.getTitle(), cmd.getDescription(), cmd.getActors(), cmd.getGenres(),
                cmd.getMinAge(), cmd.getLength()));
    }

    @Override
    public Class<? extends Command> getSupportedCommandClass() {
        return CreateMovieCommand.class;
    }
}
