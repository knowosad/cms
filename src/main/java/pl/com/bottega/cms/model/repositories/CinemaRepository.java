package pl.com.bottega.cms.model;

/**
 * Created by freszczypior on 2017-12-22.
 */
public interface CinemaRepository {

    void save(Cinema cinema);

    boolean isExist(String name, String city);
}