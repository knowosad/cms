package pl.com.bottega.cms.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by freszczypior on 2017-12-26.
 */
@Entity
@Table(name = "shows")
public class Show {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Cinema cinema;

    @ManyToOne
    private Movie movie;

    private LocalDateTime dateTime;

    public Show() {
    }

    public Show(Cinema cinema, Movie movie, LocalDateTime dateTime) {
        this.cinema = cinema;
        this.movie = movie;
        this.dateTime = dateTime;
    }
}
