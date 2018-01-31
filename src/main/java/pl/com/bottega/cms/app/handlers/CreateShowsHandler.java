package pl.com.bottega.cms.app.handlers;

import org.springframework.stereotype.Component;
import pl.com.bottega.cms.model.Show;
import pl.com.bottega.cms.model.ShowFactory;
import pl.com.bottega.cms.model.commands.Command;
import pl.com.bottega.cms.model.commands.CreateShowsCommand;
import pl.com.bottega.cms.model.repositories.CinemaRepository;
import pl.com.bottega.cms.model.repositories.ShowRepository;

import javax.transaction.Transactional;
import java.util.Collection;

/**
 * Created by freszczypior on 2017-12-26.
 */
@Component
public class CreateShowsHandler implements Handler<CreateShowsCommand>{

    private ShowRepository showRepository;
    private ShowFactory showFactory;

    public CreateShowsHandler(ShowRepository repository, ShowFactory showFactory) {
        this.showRepository = repository;
        this.showFactory = showFactory;
    }

    @Override
    @Transactional
    public void handle(CreateShowsCommand cmd) {
        Collection<Show> shows = getShows(cmd);
        shows.stream().forEach((show) -> showRepository.save(show));
    }

    private Collection<Show> getShows(CreateShowsCommand cmd) {
        return showFactory.createShows(cmd);
    }

    @Override
    public Class<? extends Command> getSupportedCommandClass() {
        return CreateShowsCommand.class;
    }
}
