package pl.com.bottega.cms.model.commands;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

/**
 * Created by freszczypior on 2017-12-25.
 */

@Getter
@Setter
public class CreateShowsCommand implements Command {
    private Long cinemaId, movieId;

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm")
    private Set<LocalDateTime> dates;

    private ShowsCalendar calendar;

    @Override
    public void validate(ValidationErrors errors) {

        validatePresence(errors, "cinemaId", cinemaId);
        validatePresence(errors, "id", movieId);

        if (bothDatesAndCalendarAreNull()) {
            errors.add("dates", "Both parameters can't be empty. Enter 'date' or 'calendar'.");
            errors.add("calendar", "Both parameters can't be empty. Enter 'date' or 'calendar'.");
        }
        if (bothDatesAndCalendarAreNotNull()) {
            errors.add("dates", "Only one of the parameters 'date' or 'calendar' should be given.");
            errors.add("calendar", "Only one of the parameters 'date' or 'calendar' should be given.");
        }
        if (calendar != null) {
            validatePresence(errors, "fromDate", calendar.getFromDate());
            validatePresence(errors, "untilDate", calendar.getUntilDate());
            validatePresence(errors, "weekDays", calendar.getWeekDays());
            validatePresence(errors, "hours", calendar.getHours());
            if (calendar.hasAllFields()) {
                validateFromDateValue(errors, calendar);
                validateWeekDaysValue(errors, calendar);
            }
        } else if(dates != null)
            validateDatesValue(errors, dates);
    }

    private void validateDatesValue(ValidationErrors errors, Set<LocalDateTime> dates) {
        if (!dates.stream().allMatch((dateTime) -> dateTime.isAfter(LocalDateTime.now()))) {
            errors.add("dates", "All dates should be in the future");
        }
    }

    private void validateWeekDaysValue(ValidationErrors errors, ShowsCalendar calendar) {
        if (!calendar.getWeekDays().stream().allMatch((day) -> ifCorrectDayOfWeek(day))) {
            errors.add("weekDays", "WeekDays should only contain valid names for the days of the week.");
        }
    }

    private Boolean ifCorrectDayOfWeek(String dayOfWeek) {
        try {
            DayOfWeek.valueOf(dayOfWeek.toUpperCase());
        } catch (IllegalArgumentException ex) {
            return false;
        }
        return true;
    }

    private void validateFromDateValue(ValidationErrors errors, ShowsCalendar calendar) {
        if (calendar.getFromDate().isAfter(calendar.getUntilDate())) {
            errors.add("fromDate", "FromDate should be a date in the future.");
        }
        if (calendar.getFromDate().isBefore(LocalDateTime.now())) {
            errors.add("fromDate", "FromDate should be before UntilDate");
        }
    }

    private Boolean bothDatesAndCalendarAreNotNull() {
        return dates != null && calendar != null;
    }

    private Boolean bothDatesAndCalendarAreNull() {
        return (dates == null || dates.isEmpty()) && calendar == null;
    }

}
