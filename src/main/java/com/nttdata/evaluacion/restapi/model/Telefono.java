package com.nttdata.evaluacion.restapi.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "telefono")
public class Telefono {   

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "number")
	private Integer number;

	@Column(name = "citycode")
	private String citycode;

	@Column(name = "countrycode")
	private String countrycode;

	public Telefono() {

	}

	public Telefono(Integer number, String citycode, String countrycode) {
		this.number = number;
		this.citycode = citycode;
		this.countrycode = countrycode;
	}

	public long getId() {
		return id;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getCitycode() {
		return citycode;
	}

	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}

	public String getContrycode() {
		return contrycode;
	}

	public void setContrycode(String contrycode) {
		this.contrycode = contrycode;
	}

	@Override
	public String toString() {
		return contrycode + "-" + citycode + "-" + number;
	}

}
