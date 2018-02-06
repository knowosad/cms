package model;

import org.junit.Before;
import org.junit.Test;
import pl.com.bottega.cms.model.commands.CreateShowsCommand;
import pl.com.bottega.cms.model.commands.ShowsCalendar;
import pl.com.bottega.cms.model.commands.ValidationErrors;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class CreateShowsCommandTest {

    ValidationErrors errors;
    CreateShowsCommand command;

    @Before
    public void clearErrors(){
        errors = new ValidationErrors();
        command = new CreateShowsCommand();
    }

    @Test
    public void shouldGenerateErrorsWhenCinemaIdOrMovieIdAreNull(){
        //given
        ShowsCalendar calendar = buildShowsCalendar(
                LocalDateTime.now().plusDays(1),
                LocalDateTime.now().plusDays(2),
                Arrays.asList(LocalTime.of(15, 00)),
                Arrays.asList("Monday"));
        command.setCalendar(calendar);
        //when
        command.validate(errors);
        //then
        assertEquals(2, errors.getErrors().size());
        assertEquals(Arrays.asList("cinemaId", "id"), listOfErrorsKeys(errors));
    }

    @Test
    public void shouldGenerateErrorsWhenBothDatesAndCalendarAreNull(){
        //given
        setCorrectMovieIdAndCinemaId();
        //when
        command.validate(errors);
        //then
        assertEquals(2, errors.getErrors().size());
        //TODO stream w listOfErrorsKeys ustawia elem w jakiejś kolejności
        assertEquals(Arrays.asList("calendar", "dates"), listOfErrorsKeys(errors));
    }

    @Test
    public void shouldGenerateErrorsWhenBothDatesAndCalendarAreNotNull(){
        //given
        setCorrectMovieIdAndCinemaId();
        ShowsCalendar calendar = buildShowsCalendar(
                LocalDateTime.now().plusDays(1),
                LocalDateTime.now().plusDays(2),
                Arrays.asList(LocalTime.of(15, 00)),
                Arrays.asList("Monday"));
        command.setCalendar(calendar);
        command.setDates(new HashSet<>(Arrays.asList(LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(2))));
        //when
        command.validate(errors);
        //then
        assertEquals(2, errors.getErrors().size());
        //TODO stream w listOfErrorsKeys ustawia elem w jakiejś kolejności
        assertEquals(Arrays.asList("calendar", "dates"), listOfErrorsKeys(errors));
    }
    @Test
    public void shouldGenerateErrorsWhenCalendarNotContainsAllRequiredValues(){
        //given
        setCorrectMovieIdAndCinemaId();
        ShowsCalendar calendar = new ShowsCalendar();
        command.setCalendar(calendar);
        //when
        command.validate(errors);
        //then
        assertEquals(4, errors.getErrors().size());
        //TODO stream w listOfErrorsKeys ustawia elem w jakiejś kolejności
        assertEquals(Arrays.asList("fromDate","hours","weekDays","untilDate"), listOfErrorsKeys(errors));
    }
    @Test
    public void shouldGenerateErrorWhenCalendarContainPastFromDate(){
        //given
        setCorrectMovieIdAndCinemaId();
        ShowsCalendar calendar = buildShowsCalendar(
                LocalDateTime.now().minusDays(1),
                LocalDateTime.now().plusDays(2),
                Arrays.asList(LocalTime.of(15, 00)),
                Arrays.asList("Monday"));
        command.setCalendar(calendar);
        //when
        command.validate(errors);
        //then
        assertEquals(1, errors.getErrors().size());
        //TODO stream w listOfErrorsKeys ustawia elem w jakiejś kolejności
        assertEquals(Arrays.asList("fromDate"), listOfErrorsKeys(errors));
    }
    @Test
    public void shouldGenerateErrorWhenInCalendarFromDateIsAfterUntilDate(){
        //given
        setCorrectMovieIdAndCinemaId();
        ShowsCalendar calendar = buildShowsCalendar(
                LocalDateTime.now().plusDays(3),
                LocalDateTime.now().plusDays(2),
                Arrays.asList(LocalTime.of(15, 00)),
                Arrays.asList("Monday"));
        command.setCalendar(calendar);
        //when
        command.validate(errors);
        //then
        assertEquals(1, errors.getErrors().size());
        //TODO stream w listOfErrorsKeys ustawia elem w jakiejś kolejności
        assertEquals(Arrays.asList("fromDate"), listOfErrorsKeys(errors));
    }
    @Test
    public void shouldGenerateErrorsWhenDatesAreInThePast(){
        //given
        setCorrectMovieIdAndCinemaId();
        command.setDates(new HashSet<>(Arrays.asList(LocalDateTime.now().minusDays(1), LocalDateTime.now().plusDays(2))));
        //when
        command.validate(errors);
        //then
        assertEquals(1, errors.getErrors().size());
        //TODO stream w listOfErrorsKeys ustawia elem w odwrotnej kolejności
        assertEquals(Arrays.asList("dates"), listOfErrorsKeys(errors));
    }

    private void setCorrectMovieIdAndCinemaId() {
        command.setCinemaId(2L);
        command.setMovieId(1L);
    }

    private ShowsCalendar buildShowsCalendar(LocalDateTime from, LocalDateTime until, Collection hours, Collection weekDays) {
        ShowsCalendar calendar = new ShowsCalendar();
        calendar.setFromDate(from);
        calendar.setUntilDate(until);
        calendar.setHours(new HashSet<>(hours));
        calendar.setWeekDays(new HashSet<>(weekDays));
        return calendar;
    }

    private List<String> listOfErrorsKeys(ValidationErrors errors) {
        return errors.getErrors().keySet().stream().collect(Collectors.toList());
    }
}
