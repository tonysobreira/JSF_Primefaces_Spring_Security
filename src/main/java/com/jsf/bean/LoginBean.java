package com.jsf.bean;

import java.io.Serializable;

import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.jsf.util.CryptoUtil;

@ManagedBean
@SessionScoped
public class LoginBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = 7413858190206623738L;
	private String email;
	private String password;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	public void login() {
		
		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(this.email, this.password);
		Authentication authentication = authenticationManager.authenticate(authRequest);
		SecurityContext securityContext = SecurityContextHolder.getContext();
	    securityContext.setAuthentication(authentication);
	    
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		try {
			if(!(password == null)){
				this.password = CryptoUtil.encryptAES(password, CryptoUtil.chaveEncriptacao);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
