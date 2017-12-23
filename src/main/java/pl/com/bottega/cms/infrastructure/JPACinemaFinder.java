package pl.com.bottega.cms.infrastructure;

import org.springframework.stereotype.Component;
import pl.com.bottega.cms.app.CinemaFinder;
import pl.com.bottega.cms.app.dtos.CinemaDto;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by freszczypior on 2017-12-23.
 */
@Component
public class JPACinemaFinder implements CinemaFinder{

    private EntityManager entityManager;

    public JPACinemaFinder(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<CinemaDto> getAll() {
        Query query = entityManager.createQuery(
                "select new pl.com.bottega.cms.app.dtos.CinemaDto(c.id, c.name, c.city) from Cinema c");
        return query.getResultList();
    }
}
