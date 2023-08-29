package com.kufar.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class UserEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	@NotBlank
	private String name;
	@Pattern(regexp = "{1,1}[мж]", message = "Your gender is wrong!")
	private String gender;
	@Pattern(regexp = "^(.+)@(\\\\S+)$", message = "Your email is not valid!")
	private String email;
	@Pattern(regexp = "/^(\\s*)?(\\+)?([- _():=+]?\\d[- _():=+]?){10,14}(\\s*)?$/", message = "Your phone number is not valid!")
	private String phNum;
	
	@ElementCollection(fetch = FetchType.EAGER)
	private List<Role> roles = new ArrayList();
	
//	@ManyToMany
//	private ArrayList<ProductEntity> products = new ArrayList();
//	
//	@ManyToMany
//	private ArrayList<ProductEntity> favProducts = new ArrayList();
	
}
