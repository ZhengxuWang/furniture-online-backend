package com.mercury.shop.bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
@Entity
@Table(name = "msi_user")
public class User implements UserDetails {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "MSI_USER_SEQ_GEN")
	@SequenceGenerator(name = "MSI_USER_SEQ_GEN", sequenceName = "MSI_USER_SEQ", allocationSize = 1)
	private int id;
	@Column
	private String username;
	@Column
	private String password;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "msi_user_msi_user_profile", joinColumns = {
			@JoinColumn(name = "user_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "user_profile_id", referencedColumnName = "id") })
	private List<UserProfile> profiles = new ArrayList<UserProfile>();
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	private UserDetail userDetail;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<UserProfile> getProfiles() {
		return profiles;
	}
	public void setProfiles(List<UserProfile> profiles) {
		this.profiles = profiles;
	}
	public UserDetail getUserDetail() {
		return userDetail;
	}
	public void setUserDetail(UserDetail userDetail) {
		this.userDetail = userDetail;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return profiles;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
}
