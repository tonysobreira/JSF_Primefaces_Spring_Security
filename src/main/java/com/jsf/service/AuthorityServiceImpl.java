package com.jsf.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsf.dao.AuthorityDao;
import com.jsf.model.Authority;

@Service("authorityService")
public class AuthorityServiceImpl implements AuthorityService {

	private AuthorityDao authorityDao;

	@Autowired
	public AuthorityServiceImpl(AuthorityDao authorityDao) {
		this.authorityDao = authorityDao;
	}

	@Override
	public List<Authority> findAllAuthority() {
		return authorityDao.findAllAuthority();
	}

	public Authority findByAuthority(String authority) {
		return authorityDao.findByAuthority(authority);
	}

	@Override
	public Authority findById(Integer id) {
		/*
		 * Authority authority = null;
		 * 
		 * Optional<Authority> optionalRole = authorityDao.findById(id);
		 * 
		 * if (optionalRole.isPresent()) { authority = optionalRole.get(); }
		 * 
		 * return authority;
		 */
		return authorityDao.findAuthorityById(id);
	}

	@Override
	public Authority save(Authority authority) {
		return authorityDao.save(authority);
	}

}