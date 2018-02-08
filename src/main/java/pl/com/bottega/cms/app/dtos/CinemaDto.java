package pl.com.bottega.cms.app.dtos;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by freszczypior on 2017-12-23.
 */
@Getter
@Setter
public class CinemaDto {

    private Long id;
    private String name;
    private String city;

    public CinemaDto(Long id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }
}
