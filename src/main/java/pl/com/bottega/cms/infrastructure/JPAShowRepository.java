package pl.com.bottega.cms.infrastructure;

import org.springframework.stereotype.Component;
import pl.com.bottega.cms.model.Show;
import pl.com.bottega.cms.model.repositories.ShowRepository;

import javax.persistence.EntityManager;

/**
 * Created by freszczypior on 2017-12-26.
 */
@Component
public class JPAShowRepository implements ShowRepository {

    private EntityManager entityManager;

    public JPAShowRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(Show show) {
        entityManager.persist(show);
    }


}
