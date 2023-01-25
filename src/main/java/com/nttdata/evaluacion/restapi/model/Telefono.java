package com.nttdata.evaluacion.restapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Telefono")
public class Telefono {   

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "number")
	private Integer number;

	@Column(name = "citycode")
	private String citycode;

	@Column(name = "contrycode")
	private String contrycode;

	public Telefono() {

	}

	public Telefono(Integer number, String citycode, String contrycode) {
		this.number = number;
		this.citycode = citycode;
		this.contrycode = contrycode;
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
