package com.company.hxs.base.dao;

import java.io.Serializable;

import org.springframework.orm.hibernate4.HibernateOperations;

public abstract interface ISqlCommonDao extends HibernateOperations {
	
	public abstract <G> G getEntity(Class<G> paramClass, Serializable paramSerializable);

	public abstract <G> void persistEntity(G paramG);

	public abstract <G> G mergeEntity(G paramG);

	public abstract <G> void removeEntity(G paramG);

}
