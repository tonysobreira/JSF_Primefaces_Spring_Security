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

import com.jsf.util.CryptoUtil;

@ManagedBean
@SessionScoped
public class LoginBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = 7413858190206623738L;
	
	private String email;
	private String password1;
	private String password2;

	@Autowired
	private AuthenticationManager authenticationManager;

	public void login() {

		String encPass = CryptoUtil.encryptAES(this.password1);
		String decPass = CryptoUtil.decryptAES(encPass);

		System.out.println("Encrypted Password: " + encPass);
		System.out.println("Decrypted Password: " + decPass);

		Authentication authRequest = new UsernamePasswordAuthenticationToken(this.email, this.password1);
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

	public String getPassword1() {
		return password1;
	}

	public void setPassword1(String password1) {
		this.password1 = password1;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

}
