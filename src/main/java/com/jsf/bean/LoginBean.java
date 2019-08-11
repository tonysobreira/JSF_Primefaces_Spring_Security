package com.jsf.bean;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.jsf.util.CryptoUtil;

@ManagedBean
@SessionScoped
public class LoginBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = 7413858190206623738L;
	private String email;
	private String password;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	//@Autowired
	//private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public void login() {
		
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		String encPass = encoder.encode(this.password);
		
		
		String pass = "";
				
//		try {
//			pass = CryptoUtil.encryptAES(this.password);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
//		try {
//			String dec = CryptoUtil.decryptAES(pass);
//			System.out.println(dec);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		//pass = bCryptPasswordEncoder.encode(this.password);
		//pass = this.password;
		
		//Authentication authRequest = new UsernamePasswordAuthenticationToken(this.email, pass);
		Authentication authRequest = new UsernamePasswordAuthenticationToken(this.email, this.password);
		Authentication authentication = authenticationManager.authenticate(authRequest);
		SecurityContext securityContext = SecurityContextHolder.getContext();
	    securityContext.setAuthentication(authentication);
	    
	    try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("/JSF_Primefaces_Spring/secure/user.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
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
		this.password = password;
	}
	
}
