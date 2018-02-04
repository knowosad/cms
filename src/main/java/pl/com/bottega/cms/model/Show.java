package pl.com.bottega.cms.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import pl.com.bottega.cms.app.dtos.ShowDtoBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by freszczypior on 2017-12-26.
 */
@Entity
@Table(name = "shows")
@Getter
@Setter
public class Show {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Cinema cinema;

    @ManyToOne
    private Movie movie;

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm")
    private LocalDateTime dateTime;

    public Show() {
    }

    public Show(Cinema cinema, Movie movie, LocalDateTime dateTime) {
        this.cinema = cinema;
        this.movie = movie;
        this.dateTime = dateTime;
    }

    public void export(ShowExporter exporter){
        exporter.addId(id);
        exporter.addTime(dateTime.toLocalTime());
    }
}
