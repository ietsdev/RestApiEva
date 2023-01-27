package com.nttdata.evaluacion.restapi.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;
import jakarta.persistence.*;

@Entity
@Table(name = "usuario")
public class Usuario {   

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    @ColumnDefault("random_uuid()")
    private UUID id;

	@Column(name = "name")
	private String name;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;

    @Column(name = "created")
	private Date created;
    
    @Column(name = "modified")
	private Date modified;    

    @Column(name = "last_login")
	private Date last_login;

    @Column(name = "token")
	private String token;

    @Column(name = "isactive")
	private Boolean isactive;

    public String getId() {
		return id.toString();
	}

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "usuario_id")
    private Set<Telefono> phones = new HashSet<>();

    public Set<Telefono> getPhones() {
        return phones;
    }

    public void setPhones(Set<Telefono> phones) {
        this.phones = phones;
    }

    public Usuario() {

	}

	public Usuario(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
        this.created = new Date();
        this.modified = new Date();
        this.last_login = new Date();
        this.isactive = true;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public Date getLast_login() {
        return last_login;
    }

    public void setLast_login(Date last_login) {
        this.last_login = last_login;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Boolean getIsactive() {
        return isactive;
    }

    public void setIsactive(Boolean isactive) {
        this.isactive = isactive;
    }
}
