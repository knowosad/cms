package pl.com.bottega.cms.model;

import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Show.class)
public abstract class Show_ {

	public static volatile SingularAttribute<Show, LocalDateTime> dateTime;
	public static volatile SingularAttribute<Show, Movie> movie;
	public static volatile SingularAttribute<Show, Long> id;
	public static volatile SingularAttribute<Show, Cinema> cinema;

}

