package com.starcast.account.model;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "accountmaster")
public class User {
	
	private Integer accountid;
    private Long mobilenumber;
    private String email;
    private String password;
    private String countrycode;
    private String passwordConfirm;
    private String username;
    private String firstname;
    private String lastname;
    private Set<Role> roles;   
    
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public Integer getAccountid() {
		return accountid;
	}

	public void setAccountid(Integer accountid) {
		this.accountid = accountid;
	}

	/**
	 * @return the mobilenumber
	 */
	public Long getMobilenumber() {
		return mobilenumber;
	}

	/**
	 * @param mobilenumber the mobilenumber to set
	 */
	public void setMobilenumber(Long mobilenumber) {
		this.mobilenumber = mobilenumber;
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

    @Transient
    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    @ManyToMany
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

	/**
	 * @return the countrycode
	 */
	public String getCountrycode() {
		return countrycode;
	}

	/**
	 * @param countrycode the countrycode to set
	 */
	public void setCountrycode(String countrycode) {
		this.countrycode = countrycode;
	}

	/**
	 * @return the username
	 */
	@Transient
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}	

}
