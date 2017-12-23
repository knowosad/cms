package pl.com.bottega.cms.infrastructure;

import org.springframework.stereotype.Component;
import pl.com.bottega.cms.model.Cinema;
import pl.com.bottega.cms.model.repositories.CinemaRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * Created by freszczypior on 2017-12-22.
 */
@Component
public class JPACinemaRepository implements CinemaRepository {

    private EntityManager entityManager;

    public JPACinemaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(Cinema cinema) {
        entityManager.persist(cinema);
    }

    @Override
    public boolean isExist(String name, String city) {
        Query query = entityManager.createQuery("" +
                "select count (*) from Cinema c where c.name like :name and c.city like :city");
        query.setParameter("name", name);
        query.setParameter("city", city);
        int amount = ((Long) query.getSingleResult()).intValue();
        return (amount > 0) ? true : false;
    }
}
