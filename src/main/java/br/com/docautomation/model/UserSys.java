package br.com.docautomation.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StringUtils;

@Entity
public class UserSys implements UserDetails {
	private static final long serialVersionUID = 1L;
	
	public static final String USUARIO_LOGADO = "USUARIO_LOGADO";

	private static final BCryptPasswordEncoder bCry = new BCryptPasswordEncoder();
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idUser;
	
	@Column(length=150)
	private String name;
	
	@Column(length=10, nullable=false)
	private int age;
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dateBirth;
	
	@Column(length=1, nullable=false)
	private String gender;
	
	private String username;
	private String password;
	
	private String userFace;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Permission> permissoes;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> auto = new ArrayList<GrantedAuthority>();
		auto.addAll(getPermissoes());
		return auto;
	}

	public void addPermissao(Permission permissao) {
		if (permissoes == null) {
			permissoes = new HashSet<Permission>();
		}
		permissoes.add(permissao);
	}
	
	public String getEncodedPassword(String pass) {
		if (!StringUtils.isEmpty(pass)) {
			return bCry.encode(pass);
		}
		return pass;
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

	@Override
	public String toString() {
		return getName();
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getDateBirth() {
		return dateBirth;
	}

	public void setDateBirth(Date dateBirth) {
		this.dateBirth = dateBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserFace() {
		return userFace;
	}

	public void setUserFace(String userFace) {
		this.userFace = userFace;
	}

	public Set<Permission> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(Set<Permission> permissoes) {
		this.permissoes = permissoes;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.username;
	}
	
}
