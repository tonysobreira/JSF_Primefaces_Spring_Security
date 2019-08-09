package com.jsf.bean;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.ManagedBean;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.enterprise.context.SessionScoped;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.jsf.model.Authority;
import com.jsf.model.User;

@ManagedBean
@SessionScoped
public class UserBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = -54255255545386196L;
	private User user;
	private String email;
	private String password1;
	private String password2;
	
	private String name;
	private String lastName;
	
	public UserBean() {
		user = new User();
	}

	public void test() {
		for (User user : getFacade().getUserListTest()) {
			System.out.println(user);
		}
	}
	
	public List<User> listUser() {
		return getFacade().listUser();
	}
	
	public void saveUser() {
		//user.setId(0);
		
		user = new User();
		
		user.setEmail(this.email);
		
		user.setAccountNonExpired(Boolean.FALSE);
		user.setAccountNonLocked(Boolean.FALSE);
		user.setCredentialsNonExpired(Boolean.FALSE);
		user.setEnabled(Boolean.TRUE);
		
		user.setName(this.name);
		user.setLastName(this.lastName);
		
		String pass = this.password1;
		
		try {
			pass = encryptAES(pass, chaveEncriptacao);
			user.setPassword(pass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		Set<Authority> authorities = new HashSet<Authority>();
		Authority authority = null;

		authority = getFacade().findAuthorityById(new Integer(2));
		authorities.add(authority);
		
		user.setAuths(authorities);
		
		getFacade().saveUser(user);
		user = new User();
	}
	
	static String IV = "AAAAAAAAAAAAAAAA";
	public static String chaveEncriptacao = "pcvil@ctit012017";
	
	public static String encryptAES(String textopuro, String chaveencriptacao) throws Exception {
		Cipher encripta = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
		SecretKeySpec key = new SecretKeySpec(chaveencriptacao.getBytes("UTF-8"), "AES");
		encripta.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(IV.getBytes("UTF-8")));
		final byte[] criptografado = encripta.doFinal(textopuro.getBytes("UTF-8"));

		String textoCriptografado = StringUtils.trim(Base64.encodeBase64String(criptografado));

		return textoCriptografado;
	}
	
	public void deleteUser(User user) {
		getFacade().deleteUser(user);
	}
	
	public List<User> getUserListTest() {
		return getFacade().getUserListTest();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public String getUsername() {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if (auth != null) {
			return SecurityContextHolder.getContext().getAuthentication().getName();
		}
		
		return "";
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
