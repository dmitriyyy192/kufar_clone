package com.kufar.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * add, delete, change, get(1/all).
 * 
 */

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class UserEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	
	@NotBlank
	@Column(name = "user_name")
	private String username;
	
	@NotBlank
	@Column(name = "user_password")
	private String password;
	
	@Pattern(regexp = "{1,1}[мж]", message = "Your gender is wrong!")
	private String gender;

	@Column(name = "user_email")
	@Pattern(regexp = "^(.+)@(\\\\S+)$", message = "Your email is not valid!")
	private String email;
	
	@Column(name = "phone_number")
	@Pattern(regexp = "/^(\\s*)?(\\+)?([- _():=+]?\\d[- _():=+]?){10,14}(\\s*)?$/", message = "Your phone number is not valid!")
	private String phNum;
	
	@ElementCollection(fetch = FetchType.EAGER)
	private List<Role> roles = new ArrayList<Role>();
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "user_product",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "product_id"))
	private ArrayList<ProductEntity> products = new ArrayList<ProductEntity>();
//	
//	@ManyToMany
//	private ArrayList<ProductEntity> favProducts = new ArrayList();
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhNum() {
		return phNum;
	}

	public void setPhNum(String phNum) {
		this.phNum = phNum;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public ArrayList<ProductEntity> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<ProductEntity> products) {
		this.products = products;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}