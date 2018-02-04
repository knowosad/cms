package pl.com.bottega.cms.app.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
public class ShowDto {

    private Long id;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime time;

    public ShowDto(Long id, LocalTime time) {
        this.id = id;
        this.time = time;
    }

    public ShowDto() {

    }
}
