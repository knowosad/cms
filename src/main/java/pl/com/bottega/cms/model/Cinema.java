package pl.com.bottega.cms.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by freszczypior on 2017-12-22.
 */
@Entity
public class Cinema {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String city;

    public Cinema(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public Cinema() {
    }
}
