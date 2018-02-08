package pl.com.bottega.cms.acceptance;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.com.bottega.cms.app.MovieFinder;
import pl.com.bottega.cms.app.dtos.MovieDto;
import pl.com.bottega.cms.app.handlers.CreateMovieHandler;
import pl.com.bottega.cms.model.commands.CreateMovieCommand;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AddMovieTest extends AcceptanceTest {


    @Autowired
    private CreateMovieHandler createMovieHandler;

    @Autowired
    private MovieFinder movieFinder;

    private CreateMovieCommand command = new CreateMovieCommand();
    private List<String> actors = new LinkedList<>();
    private List<String> genres = new LinkedList<>();

    @Test
    public void shouldAddMovie() {
        actors.add("Cezary Pazura");
        actors.add("Katarzyna Figura");
        genres.add("Komedia");

        setCommandParameters(command, "Killer", "bla bla bla", actors, genres, 15, 120);
        createMovieHandler.handle(command);
        List<MovieDto> resultList1 = movieFinder.getAll();

        setCommandParameters(command, "Anioł", "bla bla bla", actors, genres, 15, 120);
        createMovieHandler.handle(command);
        List<MovieDto> resultList2 = movieFinder.getAll();

        assertEquals(1, resultList1.size());
        assertEquals(2, resultList2.size());
        assertEquals("Killer", resultList2.get(0).getTitle());
        assertEquals("Anioł", resultList2.get(1).getTitle());
    }

    private void setCommandParameters(CreateMovieCommand command, String title, String description,
                                      List<String> actors, List<String> genres, int minAge, int length) {
        command.setTitle(title);
        command.setDescription(description);
        command.setActors(actors);
        command.setGenres(genres);
        command.setMinAge(minAge);
        command.setLength(length);
    }
}
