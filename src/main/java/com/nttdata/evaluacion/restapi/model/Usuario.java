package com.nttdata.evaluacion.restapi.model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

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
}
