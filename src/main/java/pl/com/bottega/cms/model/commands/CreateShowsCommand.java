package pl.com.bottega.cms.model.commands;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Set;

/**
 * Created by freszczypior on 2017-12-25.
 */
public class CreateShowsCommand implements Command {

    private Long cinemaId;

    private Long movieId;

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm")
    private Set<LocalDateTime> dates;

    private ShowsCalendar calendar;

    public void validate(ValidateErrors errors) {

        validatePresence(errors, "cinemaId", cinemaId);
        validatePresence(errors, "movieId", movieId);

        if (bothDatesAndCalendarAreNull()) {
            errors.add("dates", "Both parameters can't be empty. Enter 'date' or 'calendar'.");
            errors.add("movieId", "Both parameters can't be empty. Enter 'date' or 'calendar'.");
        }
        if (bothDatesAndCalendarAreNotNull()) {
            errors.add("dates", "Only one of the parameters 'date' or 'calendar' should be given.");
            errors.add("calendar", "Only one of the parameters 'date' or 'calendar' should be given.");
        }
        if (calendar != null) {
            if (fromDateIsBeforeCurrentDate()) {
                errors.add("fromDate", "FromDate should be a date in the future.");
            }
            if (fromDateIsAfterUntilDate()) {
                errors.add("fromDate", "FromDate should be before UntilDate");
            }
//            if (hoursContainIncorrectValues()) {
//                errors.add("hours", "All hours values should be between 00:00 and 23:59");
//            }
            if (weekDaysContainIncorrectValues()) {
                errors.add("weekDays", "WeekDays should only contain valid names for the days of the week.");
            }
        } else {
            if (datesContainsPastValues()) {
                errors.add("days", "All dates should be in the future");
            }
        }
    }

    private Boolean datesContainsPastValues() {
        return !dates.stream().allMatch((dateTime) -> dateTime.isAfter(LocalDateTime.now()));
    }

    private Boolean weekDaysContainIncorrectValues() {
        return !calendar.getWeekDays().stream().allMatch((day) -> ifCorrectDayOfWeek(day));
    }

    private Boolean ifCorrectDayOfWeek(String dayOfWeek) {
        try {
            DayOfWeek.valueOf(dayOfWeek.toUpperCase());
        }catch (IllegalArgumentException ex){
            return false;
        }
        return true;
    }

    private Boolean bothDatesAndCalendarAreNotNull() {
        return dates != null && calendar != null;
    }

    private Boolean bothDatesAndCalendarAreNull() {
        return (dates == null || dates.isEmpty()) && calendar == null;
    }

    private Boolean hoursContainIncorrectValues() {
        return calendar.getHours().stream().allMatch((time) -> (hoursValidation(time)));
    }

    private Boolean hoursValidation(LocalTime hour) {
        return !hour.isBefore(LocalTime.MIN) && !hour.isAfter(LocalTime.MIN);
    }

    private Boolean fromDateIsAfterUntilDate() {
        return calendar.getFromDate().isAfter(calendar.getUntilDate());
    }

    private Boolean fromDateIsBeforeCurrentDate() {
        return calendar.getFromDate().isBefore(LocalDateTime.now());
    }

    public Long getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(Long cinemaId) {
        this.cinemaId = cinemaId;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public Set<LocalDateTime> getDates() {
        return dates;
    }

    public void setDates(Set<LocalDateTime> dates) {
        this.dates = dates;
    }

    public ShowsCalendar getCalendar() {
        return calendar;
    }

    public void setCalendar(ShowsCalendar calendar) {
        this.calendar = calendar;
    }
}
