package com.jsf.bean;

import java.io.Serializable;

import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;

import com.jsf.model.User;
import com.jsf.util.CryptoUtil;

@ManagedBean
@SessionScoped
public class RegisterBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = 751435123254279035L;
	
	private String email;
	private String password1;
	private String password2;
	
	public void saveUser() {
		User user = new User();

		user.setEmail(this.email);

		user.setAccountNonExpired(Boolean.FALSE);
		user.setAccountNonLocked(Boolean.FALSE);
		user.setCredentialsNonExpired(Boolean.FALSE);
		user.setEnabled(Boolean.TRUE);

		user.setName("name" + this.email);
		user.setLastName("last name" + this.email);

		String pass = this.password1;

		try {
			pass = CryptoUtil.encryptAES(pass);
			user.setPassword(pass);
		} catch (Exception e) {
			e.printStackTrace();
		}

		User u = getFacade().saveUser(user);
		
		System.out.println(u);
		
		user = new User();
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
