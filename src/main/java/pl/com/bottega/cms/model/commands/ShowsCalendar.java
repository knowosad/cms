package pl.com.bottega.cms.model.commands;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by freszczypior on 2017-12-25.
 */
@Getter
@Setter
public class ShowsCalendar {

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm")
    private LocalDateTime fromDate;

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm")
    private LocalDateTime untilDate;

    @Getter
    private Set<String> weekDays;

    private Set<LocalTime> hours;


    public Set<LocalDateTime> generateDates() {
        List<LocalDateTime> results = new LinkedList<>();

        Set<DayOfWeek> daysOfWeek = weekDays.stream().
                        map((weekDay) -> convertToDayOfWeek(weekDay)).collect(Collectors.toSet());

        LocalDate currentDate = fromDate.toLocalDate();

        while (!currentDate.isAfter(untilDate.toLocalDate())) {
            checkCurrentDate(results, currentDate, daysOfWeek);
            currentDate = datePlusOneDay(currentDate);
        }
        return new HashSet<>(results);
    }

    private LocalDate datePlusOneDay(LocalDate currentDate) {
        return currentDate.plusDays(1);
    }

    private void checkCurrentDate(List<LocalDateTime> results, LocalDate currentDate, Set<DayOfWeek> daysOfWeek) {
        if (daysOfWeek.contains(currentDate.getDayOfWeek())) {
            hours.stream().
                    map((hour) -> LocalDateTime.of(currentDate, hour)).
                    filter((dateTime) -> dateTime.isAfter(fromDate)).
                    filter((dateTime) -> dateTime.isBefore(untilDate)).
                    forEach((dateTime) -> results.add(dateTime));
        }
    }

    private DayOfWeek convertToDayOfWeek(String weekDay) {
        return DayOfWeek.valueOf(weekDay.toUpperCase());
    }
    public boolean hasAllFields() {
        return (fromDate!=null&&untilDate!=null&&weekDays!=null&&hours!=null);
    }

}
