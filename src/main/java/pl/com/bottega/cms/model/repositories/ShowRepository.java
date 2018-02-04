package pl.com.bottega.cms.model.repositories;

import pl.com.bottega.cms.model.Show;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by freszczypior on 2017-12-26.
 */
public interface ShowRepository {

    void save (Show show);

    //List<Show> get(Long cinemaId, LocalDateTime dateTime);

}
