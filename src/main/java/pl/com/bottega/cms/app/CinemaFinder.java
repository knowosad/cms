package pl.com.bottega.cms.app;

import pl.com.bottega.cms.app.dtos.CinemaDto;

import java.util.List;

/**
 * Created by freszczypior on 2017-12-23.
 */
public interface CinemaFinder {

    List<CinemaDto> getAll();
}
