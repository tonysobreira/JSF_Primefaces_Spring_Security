package com.jsf.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.jsf.dao.AbstractJpaRepository;
import com.jsf.dao.AuthorityDao;
import com.jsf.model.Authority;

@Repository
public class AuthorityDaoImpl  extends AbstractJpaRepository<Authority, Integer> implements AuthorityDao  {

	@Override
	public Authority save(Authority authority) {
		return this.create(authority);
	}

	@Override
	public List<Authority> findAllAuthority() {
		String jpql = " SELECT a FROM Authority a ";
		Query query = this.getEntityManager().createQuery(jpql);
		return (List<Authority>) query.getResultList();
	}

	@Override
	public Authority findAuthorityById(Integer id) {
		//Authority a = (Authority) this.getEntityManager().createQuery("select a from Authority a where a.id = " + id).getSingleResult();
		//return a;
		return this.getById(Authority.class, id);
	}

	@Override
	public Authority findByAuthority(String role) {
		return this.findByAuthority(role);
	}

}
