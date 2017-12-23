package pl.com.bottega.cms.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Movie.class)
public abstract class Movie_ {

	public static volatile ListAttribute<Movie, String> actors;
	public static volatile ListAttribute<Movie, String> genres;
	public static volatile SingularAttribute<Movie, Integer> minAge;
	public static volatile SingularAttribute<Movie, Integer> length;
	public static volatile SingularAttribute<Movie, String> description;
	public static volatile SingularAttribute<Movie, Long> id;
	public static volatile SingularAttribute<Movie, String> title;

}

