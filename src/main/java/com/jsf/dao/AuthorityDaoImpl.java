package com.jsf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jsf.model.Authority;

@Repository
public class AuthorityDaoImpl  extends AbstractJpaRepository<Authority, Integer> implements AuthorityDao  {

	@Override
	public Authority save(Authority authority) {
		return this.create(authority);
	}

	@Override
	public List<Authority> findAllAuthority() {
		return this.findAllAuthority();
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
