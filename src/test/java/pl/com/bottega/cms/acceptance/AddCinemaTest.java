package pl.com.bottega.cms.acceptance;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.com.bottega.cms.app.CinemaFinder;
import pl.com.bottega.cms.app.dtos.CinemaDto;
import pl.com.bottega.cms.app.handlers.CreateCinemaHandler;
import pl.com.bottega.cms.model.commands.CommandInvalidException;
import pl.com.bottega.cms.model.commands.CreateCinemaCommand;

import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AddCinemaTest extends AcceptanceTest{

    @Autowired
    private CreateCinemaHandler createCinemaHandler;

    @Autowired
    private CinemaFinder cinemaFinder;

    CreateCinemaCommand command = new CreateCinemaCommand();

    @Test
    public void shouldAddCinema() {
        //given
        setCommandParameters(command, "Lublin", "Bajka");
        createCinemaHandler.handle(command);
        List<CinemaDto> resultList1 = cinemaFinder.getAll();

        setCommandParameters(command, "Lublin", "Kosmos");
        createCinemaHandler.handle(command);
        List<CinemaDto> resultList2 = cinemaFinder.getAll();
        //then
        assertEquals(1, resultList1.size());
        assertEquals(2, resultList2.size());
        assertEquals("Lublin", resultList2.get(0).getCity());
        assertEquals("Bajka", resultList2.get(0).getName());
        assertEquals("Lublin", resultList2.get(1).getCity());
        assertEquals("Kosmos", resultList2.get(1).getName());
        assertEquals(Long.valueOf(1), resultList2.get(0).getId());
        assertEquals(Long.valueOf(2), resultList2.get(1).getId());
    }

    @Test(expected = CommandInvalidException.class)
    public void ShouldNotAllowMultipleCreation() {
        setCommandParameters(command, "Felicity", "Lublin");
        createCinemaHandler.handle(command);

        setCommandParameters(command, "Felicity", "Lublin");
        createCinemaHandler.handle(command);
    }

    private void setCommandParameters(CreateCinemaCommand command, String city, String name) {
        command.setCity(city);
        command.setName(name);
    }
}
